package com.aashdit.digiverifier.config.admin.service;

import ch.qos.logback.core.BasicStatusManager;
import com.aashdit.digiverifier.common.model.ServiceOutcome;

import java.nio.file.Files;

import com.aashdit.digiverifier.config.admin.dto.UserDto;
import com.aashdit.digiverifier.config.admin.dto.VendorChecksDto;
import com.aashdit.digiverifier.config.admin.dto.VendorInitiatDto;
import com.aashdit.digiverifier.config.admin.dto.VendorcheckdashbordtDto;
import com.aashdit.digiverifier.config.admin.model.*;
import com.aashdit.digiverifier.config.admin.repository.*;
import com.aashdit.digiverifier.config.candidate.model.Candidate;
import com.aashdit.digiverifier.config.candidate.model.CandidateCaseDetails;
import com.aashdit.digiverifier.config.candidate.model.ConventionalCandidate;
import com.aashdit.digiverifier.config.candidate.repository.CandidateCaseDetailsRepository;
import com.aashdit.digiverifier.config.candidate.repository.CandidateRepository;
import com.aashdit.digiverifier.config.candidate.repository.ConventionalCandidateRepository;
import com.aashdit.digiverifier.config.candidate.util.CSVUtil;
import com.aashdit.digiverifier.config.candidate.util.ExcelUtil;
import com.aashdit.digiverifier.config.superadmin.dto.DashboardDto;
import com.aashdit.digiverifier.config.superadmin.model.Source;
import com.aashdit.digiverifier.config.superadmin.model.VendorCheckStatusMaster;
import com.aashdit.digiverifier.config.superadmin.repository.*;
import com.aashdit.digiverifier.utils.ApplicationDateUtils;
import com.aashdit.digiverifier.utils.AwsUtils;
import com.aashdit.digiverifier.utils.SecurityHelper;
import com.aashdit.digiverifier.vendorcheck.dto.FetchVendorConventionalCandidateDto;
import com.aashdit.digiverifier.vendorcheck.model.ConventionalVendorCandidatesSubmitted;
import com.aashdit.digiverifier.vendorcheck.model.ConventionalVendorliChecksToPerform;
import com.aashdit.digiverifier.vendorcheck.model.ModeOfVerificationStatusMaster;
import com.aashdit.digiverifier.vendorcheck.repository.ConventionalCandidatesSubmittedRepository;
import com.aashdit.digiverifier.vendorcheck.repository.ConventionalVendorCandidatesSubmittedRepository;
import com.aashdit.digiverifier.vendorcheck.repository.LiCheckToPerformRepository;
import com.aashdit.digiverifier.vendorcheck.repository.ModeOfVerificationStatusMasterRepository;
import com.aashdit.digiverifier.vendorcheck.service.liCheckToPerformService;
import com.amazonaws.services.iot.model.FleetMetricNameAndArn;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private liCheckToPerformService liCheckToPerformService;

    @Autowired
    RestTemplate restTemplate;
    public static final String DIGIVERIFIER_DOC_BUCKET_NAME = "digiverifier-new";
    @Autowired
    AmazonS3 s3Client;
    @Autowired
    ConventionalVendorCandidatesSubmittedRepository conventionalVendorCandidatesSubmittedRepository;
    @Autowired
    ConventionalCandidatesSubmittedRepository conventionalCandidatesSubmittedRepository;

    @Autowired
    AwsUtils awsUtils;
    @Autowired
    private CSVUtil cSVUtil;

    @Autowired
    private ExcelUtil excelUtil;

    @Autowired
    private AgentSampleCsvXlsMasterRepository agentSampleCsvXlsMasterRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private VendorMasterNewRepository vendorMasterNewRepository;

    @Autowired
    private VendorChecksRepository vendorChecksRepository;

    @Autowired
    private VendorUploadChecksRepository vendorUploadChecksRepository;

    @Autowired
    private SourceRepository sourceRepository;

    @Autowired
    private CandidateCaseDetailsRepository candidateCaseDetailsRepository;

    @Autowired
    private VendorCheckStatusMasterRepository vendorCheckStatusMasterRepository;

    @Transactional
    @Override
    public ServiceOutcome<UserDto> saveUser(UserDto user) {
        ServiceOutcome<UserDto> svcSearchResult = new ServiceOutcome<>();
        UserDto userDto = new UserDto();
        try {
            User result = null;
            log.debug("User object is-->" + user);
            if (user.getUserId() != null && !user.getUserId().equals(0l) && user.getUserEmailId() != null) {
                User findUserEmail = userRepository.findByUserEmailId(user.getUserEmailId());
                if (findUserEmail != null && findUserEmail.getUserId() != user.getUserId()) {
                    svcSearchResult.setData(null);
                    svcSearchResult.setOutcome(false);
                    svcSearchResult.setMessage("User Email Id already exists.Choose another Email Id");
                } else {
                    Optional<User> userObj = userRepository.findById(user.getUserId());
                    if (userObj.isPresent()) {
                        User userObj1 = userObj.get();
                        String passwoString = userObj.get().getPassword();
                        if (!user.getPassword().equals("")) {
                            userObj1.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                            userObj1.setAddlPassword(user.getPassword());
                        } else {
                            userObj1.setPassword(passwoString);
                            userObj1.setAddlPassword(userObj1.getAddlPassword());
                        }
                        userObj1.setRole(roleRepository.findById(user.getRoleId()).get());
                        userObj1.setUserFirstName(user.getUserFirstName());
                        userObj1.setUserLastName(user.getUserLastName());
                        userObj1.setUserLandlineNum(user.getUserLandlineNum());
                        userObj1.setLocation(user.getLocation());
                        userObj1.setUserMobileNum(user.getUserMobileNum());
                        userObj1.setUserEmailId(user.getUserEmailId());
                        userObj1.setLastUpdatedOn(new Date());
                        userObj1.setLastUpdatedBy(SecurityHelper.getCurrentUser());
                        userObj1.setIsActive(user.getIsActive() != null ? user.getIsActive() : userObj1.getIsActive());
                        if (SecurityHelper.getCurrentUser().getRole().getRoleCode().equals("ROLE_AGENTSUPERVISOR")) {
                            userObj1.setAgentSupervisor(SecurityHelper.getCurrentUser());
                        } else {
                            userObj1.setAgentSupervisor(user.getAgentSupervisorId() != null ? userRepository.findById(user.getAgentSupervisorId()).get() : null);
                        }
                        result = userRepository.save(userObj1);

                        BeanUtils.copyProperties(result, userDto);

                        setSomeUserDataInDTO(userDto, result);

                        svcSearchResult.setData(userDto);
                        svcSearchResult.setOutcome(true);
                        svcSearchResult.setMessage("User information Updated successfully");
                    }
                }
            } else {
                if (user.getUserEmailId() != null && user.getEmployeeId() != null) {
                    User findUserEmail = userRepository.findByUserEmailId(user.getUserEmailId());
                    if (findUserEmail != null) {
                        svcSearchResult.setData(null);
                        svcSearchResult.setOutcome(false);
                        svcSearchResult.setMessage("User Email Id exists present.Choose another Email Id");
                    } else {
                        User userObj = userRepository.findByEmployeeId(user.getEmployeeId());
                        if (userObj != null) {
                            svcSearchResult.setData(null);
                            svcSearchResult.setOutcome(false);
                            svcSearchResult.setMessage("EmployeeId already exists.Choose another EmployeeId");
                        } else {
                            User saveNewUser = new User();

                            BeanUtils.copyProperties(user, saveNewUser);

                            saveNewUser.setUserName(user.getEmployeeId());
                            saveNewUser.setAddlPassword(user.getPassword());
                            saveNewUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                            saveNewUser.setIsUserBlocked(false);
                            saveNewUser.setOrganization(organizationRepository.findById(user.getOrganizationId()).get());
                            saveNewUser.setRole(roleRepository.findById(user.getRoleId()).get());
                            saveNewUser.setIsActive(true);
                            saveNewUser.setIsLocked(false);
                            saveNewUser.setWrongLoginCount(0);
                            saveNewUser.setIsLoggedIn(false);
                            saveNewUser.setCreatedOn(new Date());
                            if (SecurityHelper.getCurrentUser().getRole().getRoleCode().equals("ROLE_AGENTSUPERVISOR")) {
                                saveNewUser.setAgentSupervisor(SecurityHelper.getCurrentUser());
                            } else {
                                saveNewUser.setAgentSupervisor(user.getAgentSupervisorId() != null ? userRepository.findById(user.getAgentSupervisorId()).get() : null);
                            }
                            saveNewUser.setCreatedBy(SecurityHelper.getCurrentUser());
                            log.debug("User username is-->" + saveNewUser.getUserName());
                            result = userRepository.save(saveNewUser);

                            BeanUtils.copyProperties(result, userDto);

                            setSomeUserDataInDTO(userDto, result);
                            svcSearchResult.setData(userDto);
                            svcSearchResult.setOutcome(true);
                            svcSearchResult.setMessage("User information saved successfully");
                        }
                    }
                }
            }
        } catch (Exception ex) {
            log.error("Exception occured in saveUser method in UserServiceImpl-->", ex);
            svcSearchResult.setData(null);
            svcSearchResult.setOutcome(false);
            svcSearchResult.setMessage("Something Went Wrong, Please Try After Sometimes");
        }
        return svcSearchResult;
    }


    private UserDto setSomeUserDataInDTO(UserDto userDto, User result) {
        userDto.setOrganizationId(result.getOrganization() != null ? result.getOrganization().getOrganizationId() : null);
        userDto.setRoleId(result.getRole().getRoleId());
        userDto.setRoleName(result.getRole().getRoleName());
        userDto.setCreatedBy(result.getCreatedBy() != null ? result.getCreatedBy().getUserFirstName() : null);
        userDto.setCreatedOn(result.getCreatedOn());
        userDto.setLastUpdatedBy(result.getLastUpdatedBy() != null ? result.getLastUpdatedBy().getUserFirstName() : "");
        userDto.setLastUpdatedOn(result.getLastUpdatedOn() != null ? result.getLastUpdatedOn() : null);
        userDto.setAgentSupervisorId(result.getAgentSupervisor() != null ? result.getAgentSupervisor().getUserId() : null);
        return userDto;
    }

    @Override
    public ServiceOutcome<List<UserDto>> getUserByOrganizationIdAndUser(Long organizationId, User user) {
        ServiceOutcome<List<UserDto>> svcSearchResult = new ServiceOutcome<List<UserDto>>();
        List<UserDto> userDtoList = new ArrayList<UserDto>();
        List<User> userList = new ArrayList<User>();
        try {
            if (user.getRole().getRoleCode().equals("ROLE_ADMIN")) {
                userList = userRepository.findAllByOrganizationOrganizationId(organizationId);
                userList = userList.stream().filter(u -> !u.getRole().getRoleCode().equals("ROLE_ADMIN")).collect(Collectors.toList());
            } else if (user.getRole().getRoleCode().equals("ROLE_PARTNERADMIN")) {
                userList = userRepository.findAllByOrganizationOrganizationId(organizationId);
                userList = userList.stream().filter(u -> !u.getRole().getRoleCode().equals("ROLE_ADMIN") && u.getUserId() != user.getUserId()).collect(Collectors.toList());
            } else {
                userList = userRepository.findAllByOrganizationOrganizationIdAndCreatedByUserId(organizationId, user.getUserId());
            }
            for (User userobj : userList) {
                if (userList != null) {
                    UserDto userDto = new UserDto();
                    BeanUtils.copyProperties(userobj, userDto);
                    setSomeUserDataInDTO(userDto, userobj);
                    userDtoList.add(userDto);
                }
            }
            if (!userDtoList.isEmpty()) {
                svcSearchResult.setData(userDtoList);
                svcSearchResult.setOutcome(true);
                svcSearchResult.setMessage("SUCCESS");
            } else {
                svcSearchResult.setData(null);
                svcSearchResult.setOutcome(false);
                svcSearchResult.setMessage("NO USER FOUND");
            }
        } catch (Exception ex) {
            log.error("Exception occured in getUserByOrganizationId method in UserServiceImpl-->" + ex);
            svcSearchResult.setData(null);
            svcSearchResult.setOutcome(false);
            svcSearchResult.setMessage("Something Went Wrong, Please Try After Sometimes.");
        }
        return svcSearchResult;
    }

    @Override
    public ServiceOutcome<UserDto> getUserById(Long userId) {
        ServiceOutcome<UserDto> svcSearchResult = new ServiceOutcome<UserDto>();
        try {
            Optional<User> user = userRepository.findById(userId);
            if (user.isPresent()) {

                UserDto userDto = new UserDto();

                BeanUtils.copyProperties(user.get(), userDto);
                setSomeUserDataInDTO(userDto, user.get());
                svcSearchResult.setData(userDto);
                svcSearchResult.setOutcome(true);
                svcSearchResult.setMessage("SUCCESS");
            } else {
                svcSearchResult.setData(null);
                svcSearchResult.setOutcome(false);
                svcSearchResult.setMessage("NO USER FOUND");
            }
        } catch (Exception ex) {
            log.error("Exception occured in getUserById method in UserServiceImpl-->" + ex);
            svcSearchResult.setData(null);
            svcSearchResult.setOutcome(false);
            svcSearchResult.setMessage("Something Went Wrong, Please Try After Sometimes.");
        }
        return svcSearchResult;
    }

    @Override
    public ServiceOutcome<User> activeAndInactiveUserById(Long userId, Boolean isActive) {
        ServiceOutcome<User> svcSearchResult = new ServiceOutcome<>();
        try {
            User result = null;
            if (userId == null || userId.equals(0l)) {
                svcSearchResult.setData(null);
                svcSearchResult.setOutcome(false);
                svcSearchResult.setMessage("Please specify User");
            } else {
                Optional<User> userObj = userRepository.findById(userId);
                if (userObj.isPresent()) {
                    User user = userObj.get();
                    user.setIsActive(isActive);
                    user.setIsUserBlocked(!isActive);
                    result = userRepository.save(user);
                    svcSearchResult.setData(result);
                    svcSearchResult.setOutcome(true);
                    if (isActive) {
                        svcSearchResult.setMessage("User activated successfully.");
                    }
                    if (!isActive) {
                        svcSearchResult.setMessage("User deactivated successfully.");
                    }
                } else {
                    svcSearchResult.setData(null);
                    svcSearchResult.setOutcome(false);
                    svcSearchResult.setMessage("No User Found");
                }
            }
        } catch (Exception ex) {
            log.error("Exception occured in activeAndInactiveUserById method in UserServiceImpl-->" + ex);
            svcSearchResult.setData(null);
            svcSearchResult.setOutcome(false);
            svcSearchResult.setMessage("Something Went Wrong, Please Try After Sometimes.");
        }
        return svcSearchResult;
    }

    @Override
    public ServiceOutcome<User> findByUsername(String userName) {

        ServiceOutcome<User> svcOutcome = new ServiceOutcome<User>();
        try {
            User user = userRepository.findByUserName(userName);
            svcOutcome.setData(user);
        } catch (Exception ex) {
            log.error("Exception occured in findByUsername method in UserServiceImpl-->" + ex);

            svcOutcome.setData(null);
            svcOutcome.setOutcome(false);
            svcOutcome.setMessage("Error");

        }
        return svcOutcome;
    }

    @Override
    public ServiceOutcome<User> saveUserLoginData(User user) {
        ServiceOutcome<User> svcOutcome = new ServiceOutcome<User>();
        try {
            user = userRepository.saveAndFlush(user);
            svcOutcome.setData(user);
        } catch (Exception ex) {
            log.error("Exception occured in save method in UserServiceImpl-->" + ex);

            svcOutcome.setData(null);
            svcOutcome.setOutcome(false);
            svcOutcome.setMessage("Error");
        }

        return svcOutcome;
    }

    @Override
    public ServiceOutcome<User> getAdminDetailsForOrganization(Long organizationId) {
        ServiceOutcome<User> svcOutcome = new ServiceOutcome<User>();
        try {
            Role role = roleRepository.findRoleByRoleCode("ROLE_ADMIN");
            User user = userRepository.findByOrganizationOrganizationIdAndRoleRoleIdAndIsActiveTrue(organizationId, role.getRoleId());
            if (user != null) {
                svcOutcome.setData(user);
                svcOutcome.setOutcome(true);
                svcOutcome.setMessage("SUCCESS");
            } else {
                svcOutcome.setData(null);
                svcOutcome.setOutcome(false);
                svcOutcome.setMessage("ADMIN NOT FOUND");
            }
        } catch (Exception ex) {
            log.error("Exception occured in getAdminDetailsForOrganization Method-->" + ex);
            svcOutcome.setData(null);
            svcOutcome.setOutcome(false);
            svcOutcome.setMessage("Something Went Wrong, Please Try After Sometimes.");
        }
        return svcOutcome;
    }

    @Override
    public ServiceOutcome<List<User>> getAgentSupervisorList(Long organizationId) {
        ServiceOutcome<List<User>> svcOutcome = new ServiceOutcome<List<User>>();
        try {
            Role role = roleRepository.findRoleByRoleCode("ROLE_AGENTSUPERVISOR");
            if (role != null) {
                List<User> userList = userRepository.findAllByOrganizationOrganizationIdAndRoleRoleIdAndIsActiveTrue(organizationId, role.getRoleId());
                if (!userList.isEmpty()) {
                    svcOutcome.setData(userList);
                    svcOutcome.setOutcome(true);
                    svcOutcome.setMessage("SUCCESS");
                } else {
                    svcOutcome.setData(null);
                    svcOutcome.setOutcome(false);
                    svcOutcome.setMessage("No Agent Supervisor found for this Organization");
                }
            }
        } catch (Exception ex) {
            log.error("Exception occured in getAgentSupervisorList Method-->" + ex);
            svcOutcome.setData(null);
            svcOutcome.setOutcome(false);
            svcOutcome.setMessage("Something Went Wrong, Please Try After Sometimes.");
        }
        return svcOutcome;
    }

    @Transactional
    @Override
    public ServiceOutcome<Boolean> saveAgentInformation(MultipartFile file) {
        ServiceOutcome<Boolean> svcSearchResult = new ServiceOutcome<Boolean>();
        AgentSampleCsvXlsMaster agentSampleCsvXlsMaster = null;
        try {
            User user = SecurityHelper.getCurrentUser();
            List<User> users = null;
            if (CSVUtil.hasCSVFormat(file)) {
                users = cSVUtil.csvToUserList(file.getInputStream());
                agentSampleCsvXlsMaster = new AgentSampleCsvXlsMaster();
                agentSampleCsvXlsMaster.setAgentSampleCsv(file.getBytes());
            }
            if (ExcelUtil.hasExcelFormat(file)) {
                users = excelUtil.excelToUserList(file.getInputStream());
                agentSampleCsvXlsMaster = new AgentSampleCsvXlsMaster();
                agentSampleCsvXlsMaster.setAgentSampleXls(file.getBytes());
            }
            for (User userObj : users) {
                userObj.setUserName(userObj.getEmployeeId());
                userObj.setOrganization(organizationRepository.findById(user.getOrganization().getOrganizationId()).get());
                userObj.setCreatedOn(new Date());
                userObj.setCreatedBy(user);
                userObj.setAddlPassword("123456");
                userObj.setPassword(bCryptPasswordEncoder.encode("123456"));
                userObj.setIsUserBlocked(false);
                userObj.setIsActive(true);
                userObj.setIsLocked(false);
                userObj.setWrongLoginCount(0);
                userObj.setIsLoggedIn(false);
                userObj.setRole(roleRepository.findRoleByRoleCode("ROLE_AGENTHR"));
            }
            List<User> userList = userRepository.saveAllAndFlush(users);
            if (!userList.isEmpty()) {
                agentSampleCsvXlsMaster.setOrganization(organizationRepository.findById(user.getOrganization().getOrganizationId()).get());
                agentSampleCsvXlsMaster.setUploadedTimestamp(new Date());
                agentSampleCsvXlsMaster.setCreatedBy(user);
                agentSampleCsvXlsMaster.setCreatedOn(new Date());
                agentSampleCsvXlsMasterRepository.save(agentSampleCsvXlsMaster);

                svcSearchResult.setData(true);
                svcSearchResult.setOutcome(true);
                svcSearchResult.setMessage("Agents uploaded successfully.");
            } else {
                svcSearchResult.setData(false);
                svcSearchResult.setOutcome(false);
                svcSearchResult.setMessage(file.getOriginalFilename() + " could not be uploaded.");
            }
        } catch (IOException e) {
            svcSearchResult.setData(false);
            svcSearchResult.setOutcome(false);
            svcSearchResult.setMessage("Unable to upload agent details.");
            log.error("Exception occured in saveAgentInformation method in UserServiceImpl-->" + e);
            throw new RuntimeException("fail to store csv/xls data: " + e.getMessage());
        }
        return svcSearchResult;
    }


    @Override
    public ServiceOutcome<UserDto> getUserProfile() {
        ServiceOutcome<UserDto> svcSearchResult = new ServiceOutcome<UserDto>();
        try {
            User user = SecurityHelper.getCurrentUser();
            Optional<User> userObj = userRepository.findById(user.getUserId());
            if (userObj.isPresent()) {
                UserDto userDto = new UserDto();
                BeanUtils.copyProperties(userObj.get(), userDto);
                setSomeUserDataInDTO(userDto, userObj.get());
                svcSearchResult.setData(userDto);
                svcSearchResult.setOutcome(true);
                svcSearchResult.setMessage("SUCCESS");
            } else {
                svcSearchResult.setData(null);
                svcSearchResult.setOutcome(false);
                svcSearchResult.setMessage("NO USER FOUND");
            }
        } catch (Exception ex) {
            log.error("Exception occured in getUserProfile method in UserServiceImpl-->" + ex);
            svcSearchResult.setData(null);
            svcSearchResult.setOutcome(false);
            svcSearchResult.setMessage("Something Went Wrong, Please Try After Sometimes.");
        }
        return svcSearchResult;
    }


    @Override
    public ServiceOutcome<List<User>> getAdminList() {
        ServiceOutcome<List<User>> svcSearchResult = new ServiceOutcome<List<User>>();
        try {
            List<User> adminUserList = userRepository.findByRoleRoleCode("ROLE_ADMIN");
            svcSearchResult.setData(adminUserList);
            svcSearchResult.setOutcome(true);
            svcSearchResult.setMessage("SUCCESS");
        } catch (Exception ex) {
            log.error("Exception occured in getAdminList method in UserServiceImpl-->" + ex);
            svcSearchResult.setData(null);
            svcSearchResult.setOutcome(false);
            svcSearchResult.setMessage("Something Went Wrong, Please Try After Sometimes.");
        }
        return svcSearchResult;
    }


    @Override
    public ServiceOutcome<User> activeNInAtiveAdmin(Long userId, Boolean isActive) {
        ServiceOutcome<User> svcSearchResult = new ServiceOutcome<User>();
        try {
            Optional<User> userObj = userRepository.findById(userId);
            if (userObj.isPresent()) {
                User user = userObj.get();
                Role role = roleRepository.findRoleByRoleCode("ROLE_ADMIN");
                User userActive = userRepository.findByOrganizationOrganizationIdAndRoleRoleIdAndIsActiveTrue(user.getOrganization().getOrganizationId(), role.getRoleId());
                if (userActive != null) {
                    svcSearchResult.setData(user);
                    svcSearchResult.setOutcome(true);
                    svcSearchResult.setMessage("Only one admin can be active in one time. Please deactivate one before continuing.");
                } else {
                    user.setIsActive(isActive);
                    user.setIsUserBlocked(!isActive);
                    user = userRepository.save(user);
                    svcSearchResult.setData(user);
                    svcSearchResult.setOutcome(true);
                    if (isActive) {
                        svcSearchResult.setMessage("Admin activated successfully.");
                    }
                    if (!isActive) {
                        svcSearchResult.setMessage("Admin deactivated successfully.");
                    }
                }

            } else {
                svcSearchResult.setData(null);
                svcSearchResult.setOutcome(false);
                svcSearchResult.setMessage("No User Found");
            }
        } catch (Exception ex) {
            log.error("Exception occured in getUserProfile method in UserServiceImpl-->" + ex);
            svcSearchResult.setData(null);
            svcSearchResult.setOutcome(false);
            svcSearchResult.setMessage("Something Went Wrong, Please Try After Sometimes.");
        }
        return svcSearchResult;
    }


    @Override
    public ServiceOutcome<List<User>> getAgentList(Long organizationId) {
        ServiceOutcome<List<User>> svcOutcome = new ServiceOutcome<List<User>>();
        try {
            User user = SecurityHelper.getCurrentUser();
            List<User> userList = new ArrayList<User>();
            Role role = roleRepository.findRoleByRoleCode("ROLE_AGENTHR");
            if (role != null) {
                if (organizationId != 0) {
                    if (user.getRole().getRoleCode().equals("ROLE_AGENTSUPERVISOR")) {
                        userList = userRepository.findAllByAgentSupervisorUserIdAndRoleRoleIdAndIsActiveTrue(user.getUserId(), role.getRoleId());
                    } else {
                        userList = userRepository.findAllByOrganizationOrganizationIdAndRoleRoleIdAndIsActiveTrue(organizationId, role.getRoleId());
                    }
                } else {
                    userList = userRepository.findAllByRoleRoleIdAndIsActiveTrue(role.getRoleId());
                }
                if (!userList.isEmpty()) {
                    svcOutcome.setData(userList);
                    svcOutcome.setOutcome(true);
                    svcOutcome.setMessage("SUCCESS");
                } else {
                    svcOutcome.setData(null);
                    svcOutcome.setOutcome(false);
                    svcOutcome.setMessage("No Agent found for this Organization");
                }
            }
        } catch (Exception ex) {
            log.error("Exception occured in getAgentList Method-->", ex);
            svcOutcome.setData(null);
            svcOutcome.setOutcome(false);
            svcOutcome.setMessage("Something Went Wrong, Please Try After Sometimes.");
        }
        return svcOutcome;
    }


    @Override
    public ServiceOutcome<User> getUserByUserId(Long userId) {
        ServiceOutcome<User> svcSearchResult = new ServiceOutcome<User>();
        try {
            Optional<User> user = userRepository.findById(userId);
            if (user.isPresent()) {

                svcSearchResult.setData(user.get());
                svcSearchResult.setOutcome(true);
                svcSearchResult.setMessage("SUCCESS");
            } else {
                svcSearchResult.setData(null);
                svcSearchResult.setOutcome(false);
                svcSearchResult.setMessage("NO USER FOUND");
            }
        } catch (Exception ex) {
            log.error("Exception occured in getUserById method in UserServiceImpl-->" + ex);
            svcSearchResult.setData(null);
            svcSearchResult.setOutcome(false);
            svcSearchResult.setMessage("Something Went Wrong, Please Try After Sometimes.");
        }
        return svcSearchResult;
    }


    @Override
    public ServiceOutcome<List<User>> getUsersByRoleCode(String roleCode) {
        roleCode = roleCode.replaceAll("\"", "");
        ServiceOutcome<List<User>> svcSearchResult = new ServiceOutcome<List<User>>();
        List<User> userList = new ArrayList<User>();
        try {
            if (roleCode.equals("ROLE_ADMIN")) {
                User user = SecurityHelper.getCurrentUser();
                userList = userRepository.findAllByOrganizationOrganizationId(user.getOrganization().getOrganizationId());
                userList = userList.stream().filter(u -> !u.getRole().getRoleCode().equals("ROLE_ADMIN")).collect(Collectors.toList());
            } else if (roleCode.equals("ROLE_PARTNERADMIN")) {
                User user = SecurityHelper.getCurrentUser();
                userList = userRepository.findAllByOrganizationOrganizationId(user.getOrganization().getOrganizationId());
                userList = userList.stream().filter(u -> !u.getRole().getRoleCode().equals("ROLE_ADMIN") && u.getUserId() != user.getUserId()).collect(Collectors.toList());
            } else if (roleCode.equals("ROLE_AGENTSUPERVISOR")) {
                User user = SecurityHelper.getCurrentUser();
                userList = userRepository.findAllByOrganizationOrganizationIdAndCreatedByUserId(user.getOrganization().getOrganizationId(), user.getUserId());
            } else {
                userList = userRepository.findByIsActiveTrue();
            }
            if (!userList.isEmpty()) {
                svcSearchResult.setData(userList);
                svcSearchResult.setOutcome(true);
                svcSearchResult.setMessage("SUCCESS");
            } else {
                svcSearchResult.setData(null);
                svcSearchResult.setOutcome(false);
                svcSearchResult.setMessage("NO USERS FOUND");
            }
        } catch (Exception ex) {
            log.error("Exception occured in getUsersByRoleCode method in UserServiceImpl-->" + ex);
            svcSearchResult.setData(null);
            svcSearchResult.setOutcome(false);
            svcSearchResult.setMessage("Something Went Wrong, Please Try After Sometimes.");
        }
        return svcSearchResult;
    }

    @Transactional
    @Override
    public void logoutUserAfter5Mins() {
        try {
            userRepository.logoutUserAfter5Mins();
        } catch (Exception ex) {
            log.error("Exception occured in logoutUserAfter5Mins method in UserServiceImpl-->", ex);
        }

    }

    @Override
    public ServiceOutcome<List<User>> getVendorList(Long organizationId) {
        ServiceOutcome<List<User>> svcOutcome = new ServiceOutcome<List<User>>();
        try {
            User user = SecurityHelper.getCurrentUser();
            List<User> userList = new ArrayList<User>();
            Role role = roleRepository.findRoleByRoleCode("ROLE_VENDOR");
            if (role != null) {
                if (organizationId != 0) {
                    if (user.getRole().getRoleCode().equals("ROLE_AGENTSUPERVISOR")) {
                        userList = userRepository.findAllByAgentSupervisorUserIdAndRoleRoleIdAndIsActiveTrue(user.getUserId(), role.getRoleId());
                    } else {
                        userList = userRepository.findAllByOrganizationOrganizationIdAndRoleRoleIdAndIsActiveTrue(organizationId, role.getRoleId());
                    }
                } else {
                    userList = userRepository.findAllByRoleRoleIdAndIsActiveTrue(role.getRoleId());
                }
                if (!userList.isEmpty()) {
                    svcOutcome.setData(userList);
                    svcOutcome.setOutcome(true);
                    svcOutcome.setMessage("SUCCESS");
                } else {
                    svcOutcome.setData(null);
                    svcOutcome.setOutcome(false);
                    svcOutcome.setMessage("No Vendor found for this Organization");
                }
            }
        } catch (Exception ex) {
            log.error("Exception occured in getAgentList Method-->", ex);
            svcOutcome.setData(null);
            svcOutcome.setOutcome(false);
            svcOutcome.setMessage("Something Went Wrong, Please Try After Sometimes.");
        }
        return svcOutcome;
    }


    @Override
    public ServiceOutcome<List<VendorChecks>> getVendorCheckDetails(Long candidateId) {
        ServiceOutcome<List<VendorChecks>> svcSearchResult = new ServiceOutcome<List<VendorChecks>>();
        try {
            System.out.println(candidateId);


            List<VendorChecks> vendorList = vendorChecksRepository.findAllByCandidateCandidateId(candidateId);
            if (!vendorList.isEmpty()) {
                svcSearchResult.setData(vendorList);
                svcSearchResult.setOutcome(true);
                svcSearchResult.setMessage("SUCCESS");
            } else {
                svcSearchResult.setData(null);
                svcSearchResult.setOutcome(false);
                svcSearchResult.setMessage("NO VENDORCHECKS FOUND");
            }
        } catch (Exception ex) {
            log.error("Exception occured in getVendorCheckDetails method in userServiceImpl-->" + ex);
            svcSearchResult.setData(null);
            svcSearchResult.setOutcome(false);
            svcSearchResult.setMessage("Something Went Wrong, Please Try After Sometimes.");
        }
        return svcSearchResult;
    }

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);
    @Autowired
    LiCheckToPerformRepository liCheckToPerformRepository;
    @Autowired
    ModeOfVerificationStatusMasterRepository modeOfVerificationStatusMasterRepository;

    @Override
    public ServiceOutcome<List<VendorChecksDto>> getallVendorCheckDetails(DashboardDto dashboardDto) {
        ServiceOutcome<List<VendorChecksDto>> svcSearchResult = new ServiceOutcome<List<VendorChecksDto>>();

        try {
            List<VendorChecks> vendorList = new ArrayList<>();
            List<VendorChecksDto> vendorChecksDtos = new ArrayList<>();
            String strToDate = "";
            String strFromDate = "";
            strToDate = dashboardDto.getToDate() != null ? dashboardDto.getToDate() : ApplicationDateUtils.getStringTodayAsDDMMYYYY();
            strFromDate = dashboardDto.getFromDate() != null ? dashboardDto.getFromDate() : ApplicationDateUtils.subtractNoOfDaysFromDateAsDDMMYYYY(new SimpleDateFormat("dd/MM/yyyy").parse(strToDate), 7);
            Date startDate = formatter.parse(strFromDate + " 00:00:00");
            Date endDate = formatter.parse(strToDate + " 23:59:59");

            vendorList = vendorChecksRepository.findAllByDateRange(dashboardDto.getUserId(), startDate, endDate);
            vendorList.forEach(vc -> {
                VendorChecksDto vendorChecksDto = new VendorChecksDto();
                vendorChecksDto.setAddress(vc.getAddress());
                vendorChecksDto.setAgentUploadedDocument(vc.getAgentUploadedDocument());
                vendorChecksDto.setAlternateContactNo(vc.getAlternateContactNo());
                vendorChecksDto.setCandidate(vc.getCandidate());
                vendorChecksDto.setCandidateName(vc.getCandidateName());
                ConventionalVendorliChecksToPerform byVendorChecksVendorcheckId = liCheckToPerformRepository.findByVendorChecksVendorcheckId(vc.getVendorcheckId());
                if (byVendorChecksVendorcheckId != null) {
                    vendorChecksDto.setCheckUniqueId(String.valueOf(byVendorChecksVendorcheckId.getCheckUniqueId()));
                    vendorChecksDto.setVendorCheckStatusMaster(byVendorChecksVendorcheckId.getCheckStatus());
                    ModeOfVerificationStatusMaster modeOfVerificationStatusMaster = modeOfVerificationStatusMasterRepository.findById(Long.valueOf(byVendorChecksVendorcheckId.getModeOfVerificationRequired())).get();
                    vendorChecksDto.setModeOfVerificationPerformed(modeOfVerificationStatusMaster.getModeOfVerification());
                }
                vendorChecksDto.setContactNo(vc.getContactNo());
                vendorChecksDto.setCreatedBy(vc.getCreatedBy());
                vendorChecksDto.setCreatedOn(vc.getCreatedOn());
                vendorChecksDto.setDateOfBirth(vc.getDateOfBirth());
                vendorChecksDto.setDocumentname(vc.getDocumentname());
                vendorChecksDto.setEmailId(vc.getEmailId());
                vendorChecksDto.setExpireson(vc.getExpireson());
                vendorChecksDto.setFatherName(vc.getFatherName());
                vendorChecksDto.setIsproofuploaded(vc.getIsproofuploaded());
                vendorChecksDto.setPathKey(vc.getPathKey());
                vendorChecksDto.setSource(vc.getSource());
                vendorChecksDto.setTat(vc.getTat());
                vendorChecksDto.setTypeOfPanel(vc.getTypeOfPanel());
                vendorChecksDto.setVendorcheckId(vc.getVendorcheckId());
                vendorChecksDto.setVendorId(vc.getVendorId());
                vendorChecksDtos.add(vendorChecksDto);
            });
            List<VendorChecksDto> collect = vendorChecksDtos.stream().filter(vc -> vc.getCheckUniqueId() != null).collect(Collectors.toList());
            if (!vendorList.isEmpty()) {
                svcSearchResult.setData(collect);
                svcSearchResult.setOutcome(true);
                svcSearchResult.setMessage("SUCCESS");
            } else {
                svcSearchResult.setData(null);
                svcSearchResult.setOutcome(false);
                svcSearchResult.setMessage("NO VENDORCHECKS FOUND");
            }
        } catch (Exception ex) {
            log.error("Exception occured in getVendorCheckDetails method in userServiceImpl-->" + ex);
            svcSearchResult.setData(null);
            svcSearchResult.setOutcome(false);
            svcSearchResult.setMessage("Something Went Wrong, Please Try After Sometimes.");
        }
        return svcSearchResult;
    }

    @Override
    public ServiceOutcome<List<VendorChecks>> getallVendorChecsa(Long venorId) {
        ServiceOutcome<List<VendorChecks>> svcSearchResult = new ServiceOutcome<List<VendorChecks>>();

        try {
            List<VendorChecks> vendorList = new ArrayList<>();
            vendorList = vendorChecksRepository.findAllByVendorId(venorId);


            if (!vendorList.isEmpty()) {
                svcSearchResult.setData(vendorList);
                svcSearchResult.setOutcome(true);
                svcSearchResult.setMessage("SUCCESS");
            } else {
                svcSearchResult.setData(null);
                svcSearchResult.setOutcome(false);
                svcSearchResult.setMessage("NO VENDORCHECKS FOUND");
            }
        } catch (Exception ex) {
            log.error("Exception occured in getVendorCheckDetails method in userServiceImpl-->" + ex);
            svcSearchResult.setData(null);
            svcSearchResult.setOutcome(false);
            svcSearchResult.setMessage("Something Went Wrong, Please Try After Sometimes.");
        }
        return svcSearchResult;
    }


    @Override
    @Transactional
    public ServiceOutcome<VendorChecks> saveproofuploadVendorChecks(String vendorChecksString, MultipartFile proofDocumentNew, String vendorRemarksReport) {
//        System.out.println(proofDocumentNew + "===========================" + vendorChecksString);
        ServiceOutcome<VendorChecks> svcSearchResult = new ServiceOutcome<VendorChecks>();
        VendorUploadChecks result = null;
        try {
            VendorcheckdashbordtDto vendorcheckdashbordtDto = new ObjectMapper().readValue(vendorChecksString, VendorcheckdashbordtDto.class);
            VendorChecks vendorCheckss = vendorChecksRepository.findByVendorcheckId(vendorcheckdashbordtDto.getVendorcheckId());
            VendorUploadChecks vendorUploadChecks = vendorUploadChecksRepository.findByVendorChecksVendorcheckId(vendorcheckdashbordtDto.getVendorcheckId());
            User user = SecurityHelper.getCurrentUser();
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> agentAttributeMap = objectMapper.readValue(vendorRemarksReport, new TypeReference<Map<String, String>>() {
            });
            ArrayList<String> agentAttributeList = new ArrayList<>();
            for (Map.Entry<String, String> entry : agentAttributeMap.entrySet()) {
                String concatenated = entry.getKey() + "=" + entry.getValue();
                agentAttributeList.add(concatenated);
            }

            if (vendorUploadChecks == null) {
                VendorUploadChecks vendorUploadCheckNew = new VendorUploadChecks();
                System.out.println("-------------create------");
                byte[] vendorProof = proofDocumentNew.getBytes();
                if (vendorProof != null) {
                    vendorUploadCheckNew.setVendorUploadedDocument(vendorProof);
                }
//                vendorUploadCheckNew.setVendorUploadedDocument(proofDocumentNew != null ? proofDocumentNew.getBytes() : null);
                vendorUploadCheckNew.setAgentColor(colorRepository.findById(vendorcheckdashbordtDto.getColorid()).get());
                vendorUploadCheckNew.setCreatedOn(new Date());
                vendorUploadCheckNew.setCreatedBy(user);
                vendorUploadCheckNew.setVendorChecks(vendorCheckss);
                vendorUploadCheckNew.setDocumentname(vendorcheckdashbordtDto.getDocumentname());
                vendorUploadCheckNew.setVendorAttirbuteValue(agentAttributeList);
                System.out.println("-------------------==========getVendorCheckStatusMasterId");
                result = vendorUploadChecksRepository.save(vendorUploadCheckNew);
//                System.out.println(result + "-------------------==========result");
                if (result != null) {
                    VendorChecks vendorChecksnew = vendorChecksRepository.findByVendorcheckId(vendorcheckdashbordtDto.getVendorcheckId());
                    vendorChecksnew.setIsproofuploaded(true);
                    VendorCheckStatusMaster byId = vendorCheckStatusMasterRepository.getById(Long.valueOf(vendorcheckdashbordtDto.getStatus()));
                    if (byId != null) {
                        vendorChecksnew.setVendorCheckStatusMaster(byId);
                    }
                    vendorChecksRepository.save(vendorChecksnew);
                    liCheckToPerformService.UpdateBGVCheckStatusRowwise(vendorChecksString, proofDocumentNew, "ONLINE");
                    svcSearchResult.setMessage("vendorchecks document saved successfully.");

                } else {
                    System.out.println("-------------candidate-----else------");
                    svcSearchResult.setData(null);
                    svcSearchResult.setOutcome(false);
                    // svcSearchResult.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
                }

            } else {
                System.out.println("-------------update------");
                vendorUploadChecks.setVendorUploadedDocument(proofDocumentNew != null ? proofDocumentNew.getBytes() : null);
                vendorUploadChecks.setAgentColor(colorRepository.findById(vendorcheckdashbordtDto.getColorid()).get());
                vendorUploadChecks.setCreatedOn(new Date());
                vendorUploadChecks.setCreatedBy(user);
                vendorUploadChecks.setDocumentname(vendorcheckdashbordtDto.getDocumentname());
                vendorUploadChecks.setVendorAttirbuteValue(agentAttributeList);

                result = vendorUploadChecksRepository.save(vendorUploadChecks);

                if (result != null) {
                    VendorChecks vendorChecksnew = vendorChecksRepository.findByVendorcheckId(vendorcheckdashbordtDto.getVendorcheckId());
                    vendorChecksnew.setIsproofuploaded(true);
                    VendorCheckStatusMaster byId = vendorCheckStatusMasterRepository.getById(Long.valueOf(vendorcheckdashbordtDto.getStatus()));
                    if (byId != null) {
                        vendorChecksnew.setVendorCheckStatusMaster(byId);
                    }
                    vendorChecksRepository.save(vendorChecksnew);

                    liCheckToPerformService.UpdateBGVCheckStatusRowwise(vendorChecksString, proofDocumentNew, "ONLINE");

                    svcSearchResult.setMessage("vendorchecks document update successfully.");

                } else {
                    System.out.println("-------------candidate-----else------");
                    svcSearchResult.setData(null);
                    svcSearchResult.setOutcome(false);
                }

            }


        } catch (Exception ex) {
            log.error("Exception occured in saveproofuploadVendorChecks method in userServiceImpl-->" + ex);

        }
        return svcSearchResult;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServiceOutcome<VendorChecks> saveInitiateVendorChecks(String vendorChecksString, MultipartFile proofDocumentNew, String documentUrl) throws IOException {
        ServiceOutcome<VendorChecks> svcSearchResult = new ServiceOutcome<VendorChecks>();
        try {
            Candidate Candidatelist = null;
            Long vendorid = null;
            CandidateCaseDetails result = null;
            VendorInitiatDto vendorInitiatDto = new ObjectMapper().readValue(vendorChecksString, VendorInitiatDto.class);
            FetchVendorConventionalCandidateDto fetchVendorConventionalCandidateDto = new FetchVendorConventionalCandidateDto();
            fetchVendorConventionalCandidateDto.setRequestId(String.valueOf(vendorInitiatDto.getCandidateId()));
            fetchVendorConventionalCandidateDto.setSourceId(vendorInitiatDto.getSourceId());
            fetchVendorConventionalCandidateDto.setVendorId(String.valueOf(vendorInitiatDto.getVendorId()));
            fetchVendorConventionalCandidateDto.setLicheckId(Long.valueOf(vendorInitiatDto.getLicheckId()));
            fetchVendorConventionalCandidateDto.setSourceName(vendorInitiatDto.getSourceName());
            fetchVendorConventionalCandidateDto.setVendorName(vendorInitiatDto.getVendorName());
            liCheckToPerformService.addUpdateLiCheckToPerformData(fetchVendorConventionalCandidateDto);
            byte[] candidateDocument = new byte[0];
            if (vendorInitiatDto.getDocumentUrl() != null) {
                S3Object object = s3Client.getObject(DIGIVERIFIER_DOC_BUCKET_NAME, vendorInitiatDto.getDocumentUrl());
                InputStream inputStream = object.getObjectContent();
                candidateDocument = StreamUtils.copyToByteArray(inputStream);
                String output = documentUrl.replaceAll("\"", "");
                documentUrl = output;
            }
            User user = SecurityHelper.getCurrentUser();
            System.out.println("user = " + user);
            Source source = sourceRepository.findById(vendorInitiatDto.getSourceId()).get();
            if (vendorInitiatDto.getDocumentname() != null && vendorInitiatDto.getDocumentname().equalsIgnoreCase("NA") == false) {
                //candidate id means request id
                VendorChecks vendorChecksobj = vendorChecksRepository.findByCandidateCandidateIdAndVendorIdAndSourceSourceIdAndDocumentnameAndLicheckId(vendorInitiatDto.getVendorId(), vendorInitiatDto.getCandidateId(), vendorInitiatDto.getSourceId(), vendorInitiatDto.getDocumentname(), Long.valueOf(vendorInitiatDto.getLicheckId()));
                if (vendorChecksobj != null) {
                    if (vendorInitiatDto.getDocumentUrl() != null) {
                        vendorChecksobj.setAgentUploadedDocument(candidateDocument);
                    }
//                    else {
//                        vendorChecksobj.setAgentUploadedDocument(proofDocumentNew != null ? proofDocumentNew.getBytes() : null);
//                    }
                    if (documentUrl != null) {
                        vendorChecksobj.setPathKey(documentUrl);
                    }
                    if (vendorInitiatDto.getDocumentname() != null) {
                        vendorChecksobj.setDocumentname(vendorInitiatDto.getDocumentname());
                    }              //candidate id means request id
                    Candidate byCandidateId = candidateRepository.findByConventionalRequestId(vendorInitiatDto.getCandidateId());
                    ConventionalVendorCandidatesSubmitted conventionalVendorCandidatesSubmitted = conventionalCandidatesSubmittedRepository.findByRequestId(String.valueOf(byCandidateId.getConventionalRequestId()));
                    vendorChecksobj.setCandidateName(conventionalVendorCandidatesSubmitted.getName());
                    vendorChecksobj.setCreatedBy(user);
                    if (vendorInitiatDto.getLicheckId() != null) {
                        vendorChecksobj.setLicheckId(Long.valueOf(vendorInitiatDto.getLicheckId()));
                    }
                    vendorChecksobj.setDateOfBirth(vendorInitiatDto.getDateOfBirth());
                    vendorChecksobj.setContactNo(vendorInitiatDto.getContactNo());
                    vendorChecksobj.setFatherName(vendorInitiatDto.getFatherName());
                    vendorChecksobj.setAddress(vendorInitiatDto.getAddress());
                    vendorChecksobj.setAlternateContactNo(vendorInitiatDto.getAlternateContactNo());
                    vendorChecksobj.setTypeOfPanel(vendorInitiatDto.getTypeOfPanel());
                    vendorChecksobj.setCreatedOn(new Date());
                    VendorChecks save = vendorChecksRepository.save(vendorChecksobj);
                    svcSearchResult.setMessage("vendor Checks document update successfully.");
//                    svcSearchResult.setData(vendorChecksobj);
                    svcSearchResult.setStatus(String.valueOf(save.getVendorcheckId()));
                } else {
                    VendorChecks vendorChecks = new VendorChecks();
                    //candidate id means request id
                    Candidate byCandidateId = candidateRepository.findByConventionalRequestId(vendorInitiatDto.getCandidateId());
                    vendorChecks.setCandidate(byCandidateId);
                    if (vendorInitiatDto.getDocumentUrl() != null) {
                        vendorChecks.setAgentUploadedDocument(candidateDocument);
                    }
                    if (documentUrl != null) {
                        vendorChecks.setPathKey(documentUrl);
                        vendorChecks.setDocumentname(vendorInitiatDto.getDocumentname());
                    }
                    ConventionalVendorCandidatesSubmitted conventionalVendorCandidatesSubmitted = conventionalCandidatesSubmittedRepository.findByRequestId(String.valueOf(byCandidateId.getConventionalRequestId()));
                    vendorChecks.setCandidateName(conventionalVendorCandidatesSubmitted.getName());
                    vendorChecks.setVendorId(vendorInitiatDto.getVendorId());
                    vendorChecks.setSource(sourceRepository.findById(vendorInitiatDto.getSourceId()).get());
                    vendorChecks.setDateOfBirth(vendorInitiatDto.getDateOfBirth());
                    vendorChecks.setCreatedBy(user);
                    vendorChecks.setContactNo(vendorInitiatDto.getContactNo());
                    vendorChecks.setFatherName(vendorInitiatDto.getFatherName());
                    vendorChecks.setAddress(vendorInitiatDto.getAddress());
                    vendorChecks.setAlternateContactNo(vendorInitiatDto.getAlternateContactNo());
                    vendorChecks.setTypeOfPanel(vendorInitiatDto.getTypeOfPanel());
                    vendorChecks.setCreatedOn(new Date());
                    if (vendorInitiatDto.getLicheckId() != null) {
                        vendorChecks.setLicheckId(Long.valueOf(vendorInitiatDto.getLicheckId()));
                    }
                    VendorChecks save = vendorChecksRepository.save(vendorChecks);
//                    svcSearchResult.setData(save);
                    svcSearchResult.setMessage("vendor Checks document saved successfully.");
                    svcSearchResult.setStatus(String.valueOf(save.getVendorcheckId()));
                }

            } else {
                VendorChecks vendorChecksobj = vendorChecksRepository.findByCandidateCandidateIdAndVendorIdAndSourceSourceIdAndDocumentnameAndLicheckId(vendorInitiatDto.getVendorId(), vendorInitiatDto.getCandidateId(), vendorInitiatDto.getSourceId(), vendorInitiatDto.getDocumentname(), Long.valueOf(vendorInitiatDto.getLicheckId()));
                if (vendorChecksobj != null) {
                    if (vendorInitiatDto.getDocumentUrl() != null) {
                        vendorChecksobj.setAgentUploadedDocument(candidateDocument);
                    }
//                    else {
//                        vendorChecksobj.setAgentUploadedDocument(proofDocumentNew != null ? proofDocumentNew.getBytes() : null);
//                    }

                    if (documentUrl != null) {
                        vendorChecksobj.setPathKey(documentUrl);
                        vendorChecksobj.setDocumentname(vendorInitiatDto.getDocumentname());
                    }
                    vendorChecksobj.setCreatedBy(user);
                    vendorChecksobj.setDateOfBirth(vendorInitiatDto.getDateOfBirth());
                    vendorChecksobj.setContactNo(vendorInitiatDto.getContactNo());
                    vendorChecksobj.setFatherName(vendorInitiatDto.getFatherName());
                    vendorChecksobj.setAddress(vendorInitiatDto.getAddress());
                    vendorChecksobj.setAlternateContactNo(vendorInitiatDto.getAlternateContactNo());
                    vendorChecksobj.setTypeOfPanel(vendorInitiatDto.getTypeOfPanel());
                    vendorChecksobj.setCreatedOn(new Date());
                    vendorChecksobj.setDocumentname(vendorInitiatDto.getDocumentname());
                    if (vendorInitiatDto.getLicheckId() != null) {
                        vendorChecksobj.setLicheckId(Long.valueOf(vendorInitiatDto.getLicheckId()));
                    }

                    VendorChecks save = vendorChecksRepository.save(vendorChecksobj);
//                    svcSearchResult.setData(save);

                    svcSearchResult.setMessage("vendor Checks  update successfully.");
                    svcSearchResult.setStatus(String.valueOf(save.getVendorcheckId()));
                } else {

                    VendorChecks vendorChecks = new VendorChecks();
                    //candidate id means request id
                    vendorChecks.setCandidate(candidateRepository.findByConventionalRequestId(vendorInitiatDto.getCandidateId()));
                    vendorChecks.setVendorId(vendorInitiatDto.getVendorId());
                    vendorChecks.setSource(sourceRepository.findById(vendorInitiatDto.getSourceId()).get());
                    if (vendorInitiatDto.getDocumentname() != null) {
                        vendorChecks.setDocumentname(vendorInitiatDto.getDocumentname());
                    }
                    if (vendorInitiatDto.getDocumentUrl() != null) {
                        vendorChecks.setAgentUploadedDocument(candidateDocument);
                    }
//                    else {
//                        vendorChecksobj.setAgentUploadedDocument(proofDocumentNew != null ? proofDocumentNew.getBytes() : null);
//                    }
                    vendorChecks.setCreatedBy(user);
                    vendorChecks.setCandidateName(vendorInitiatDto.getCandidateName());
                    vendorChecks.setDateOfBirth(vendorInitiatDto.getDateOfBirth());
                    vendorChecks.setContactNo(vendorInitiatDto.getContactNo());
                    vendorChecks.setFatherName(vendorInitiatDto.getFatherName());
                    vendorChecks.setAddress(vendorInitiatDto.getAddress());
                    vendorChecks.setAlternateContactNo(vendorInitiatDto.getAlternateContactNo());
                    vendorChecks.setTypeOfPanel(vendorInitiatDto.getTypeOfPanel());
                    vendorChecks.setCreatedOn(new Date());
                    if (vendorInitiatDto.getLicheckId() != null) {
                        vendorChecks.setLicheckId(Long.valueOf(vendorInitiatDto.getLicheckId()));
                    }
                    VendorChecks save = vendorChecksRepository.save(vendorChecks);
//                    svcSearchResult.setData(save);
                    svcSearchResult.setMessage("vendor Checks saved successfully.");
                    svcSearchResult.setStatus(String.valueOf(save.getVendorcheckId()));
                }

            }


        } catch (Exception ex) {
            log.error("Exception occured in saveInitiateVendorChecks method in userServiceImpl-->" + ex);

        }
//        System.out.println(svcSearchResult.getData());
        return svcSearchResult;
    }

//    public String uploadVendorRemarksForChecks(Long vendorCheckId, String vendorRemarksJson) {
//        try {
//
//        } catch (Exception e) {
//
//        }
//
//    }

}