package com.aashdit.digiverifier.config.superadmin.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.aashdit.digiverifier.common.model.ServiceOutcome;
import com.aashdit.digiverifier.config.candidate.dto.CandidateStatusCountDto;
import com.aashdit.digiverifier.config.candidate.model.CandidateStatus;
import com.aashdit.digiverifier.config.candidate.model.CandidateStatusHistory;
import com.aashdit.digiverifier.config.candidate.model.StatusMaster;
import com.aashdit.digiverifier.config.candidate.repository.CandidateStatusHistoryRepository;
import com.aashdit.digiverifier.config.candidate.repository.CandidateStatusRepository;
import com.aashdit.digiverifier.config.candidate.repository.StatusMasterRepository;
import com.aashdit.digiverifier.config.superadmin.dto.DashboardDto;
import com.aashdit.digiverifier.config.superadmin.dto.OrganizationDto;
import com.aashdit.digiverifier.config.superadmin.dto.ServiceConfigdashboardDto;
import com.aashdit.digiverifier.config.superadmin.dto.SuperAdminDashboardDto;
import com.aashdit.digiverifier.config.superadmin.model.Organization;
import com.aashdit.digiverifier.config.superadmin.repository.OrganizationRepository;
import com.aashdit.digiverifier.utils.ApplicationDateUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SuperAdminDashboardServiceImpl implements SuperAdminDashboardService,MessageSourceAware {
	
	private MessageSource messageSource;

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	@Autowired
	private CandidateStatusRepository candidateStatusRepository;
	
	@Autowired
	private CandidateStatusHistoryRepository candidateStatusHistoryRepository;
	
	@Autowired
	private StatusMasterRepository statusMasterRepository;
	
	@Autowired
	private OrganizationRepository organizationRepository;
	
	@Autowired
	private OrganizationServiceImpl organizationServiceImpl;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	@Override
	public ServiceOutcome<SuperAdminDashboardDto> getPendingDetails(SuperAdminDashboardDto superAdminDashboardDto) {
		ServiceOutcome<SuperAdminDashboardDto> svcSearchResult = new ServiceOutcome<SuperAdminDashboardDto>();
		List<CandidateStatusCountDto> candidateStatusCountDtoList=new ArrayList<CandidateStatusCountDto>();
		String strToDate="";
		String strFromDate="";
		try {
			if(superAdminDashboardDto.getOrganizationId()!=null) {
				strToDate = superAdminDashboardDto.getToDate() != null?superAdminDashboardDto.getToDate():ApplicationDateUtils.getStringTodayAsDDMMYYYY();
				strFromDate = superAdminDashboardDto.getFromDate() != null?superAdminDashboardDto.getFromDate():ApplicationDateUtils.subtractNoOfDaysFromDateAsDDMMYYYY(new SimpleDateFormat("dd/MM/yyyy").parse(strToDate),7);
				Date startDate = format.parse(strFromDate+" 00:00:00");
				Date endDate = format.parse(strToDate+" 23:59:59");
				List<CandidateStatus> candidateStatusList = new ArrayList<CandidateStatus>();
				if(superAdminDashboardDto.getOrganizationId()!=0) {
					candidateStatusList = candidateStatusRepository.findAllByOrganizationIdAndDateRange(superAdminDashboardDto.getOrganizationId(),startDate,endDate);
				}else {
					candidateStatusList = candidateStatusRepository.findAllByDateRange(startDate,endDate);
				}
				StatusMaster invStatusMaster = statusMasterRepository.findByStatusCode("INVITATIONSENT");
				StatusMaster digiStatusMaster = statusMasterRepository.findByStatusCode("DIGILOCKER");
				StatusMaster itrStatusMaster = statusMasterRepository.findByStatusCode("ITR");
				StatusMaster epfoStatusMaster = statusMasterRepository.findByStatusCode("EPFO");
				StatusMaster relStatusMaster = statusMasterRepository.findByStatusCode("RELATIVEADDRESS");
				StatusMaster canStatusMaster = statusMasterRepository.findByStatusCode("PROCESSDECLINED");
				
				List<CandidateStatus> invitationSentList = candidateStatusList != null ?candidateStatusList.stream().filter(c -> c.getStatusMaster().getStatusCode().equals("INVITATIONSENT")).collect(Collectors.toList()) : null;
				List<CandidateStatus> digiList = candidateStatusList != null ?candidateStatusList.stream().filter(c -> c.getStatusMaster().getStatusCode().equals("DIGILOCKER")).collect(Collectors.toList()) : null;
				List<CandidateStatus> itrList = candidateStatusList != null ?candidateStatusList.stream().filter(c -> c.getStatusMaster().getStatusCode().equals("ITR")).collect(Collectors.toList()) : null;
				List<CandidateStatus> epfoList = candidateStatusList != null ?candidateStatusList.stream().filter(c -> c.getStatusMaster().getStatusCode().equals("EPFO")).collect(Collectors.toList()) : null;
				candidateStatusCountDtoList.add(new CandidateStatusCountDto(invStatusMaster.getStatusName(),invStatusMaster.getStatusCode(),invitationSentList.size()));
				candidateStatusCountDtoList.add(new CandidateStatusCountDto(digiStatusMaster.getStatusName(),digiStatusMaster.getStatusCode(),digiList.size()));
				candidateStatusCountDtoList.add(new CandidateStatusCountDto(itrStatusMaster.getStatusName(),itrStatusMaster.getStatusCode(),itrList.size()));
				candidateStatusCountDtoList.add(new CandidateStatusCountDto(epfoStatusMaster.getStatusName(),epfoStatusMaster.getStatusCode(),epfoList.size()));
				List<CandidateStatus> relList = candidateStatusList != null ?candidateStatusList.stream().filter(c -> c.getStatusMaster().getStatusCode().equals("RELATIVEADDRESS")).collect(Collectors.toList()) : null;
				candidateStatusCountDtoList.add(new CandidateStatusCountDto(relStatusMaster.getStatusName(),relStatusMaster.getStatusCode(),relList.size()));
				List<CandidateStatus> canList = candidateStatusList != null ?candidateStatusList.stream().filter(c -> c.getStatusMaster().getStatusCode().equals("PROCESSDECLINED")).collect(Collectors.toList()) : null;
				candidateStatusCountDtoList.add(new CandidateStatusCountDto(canStatusMaster.getStatusName(),canStatusMaster.getStatusCode(),canList.size()));
				List<CandidateStatus> epfoSkippedList = candidateStatusList != null ?candidateStatusList.stream().filter(c ->c.getCandidate().getIsUanSkipped() != null ? c.getStatusMaster().getStatusCode().equals("DIGILOCKER")&&c.getCandidate().getIsUanSkipped()||
						c.getStatusMaster().getStatusCode().equals("ITR")&&c.getCandidate().getIsUanSkipped():false).collect(Collectors.toList()) : null;
				candidateStatusCountDtoList.add(new CandidateStatusCountDto("EPFO Skipped","EPFOSKIPPED",epfoSkippedList.size()));
				
				SuperAdminDashboardDto superAdminDashboardDtoObj=new SuperAdminDashboardDto(strFromDate,strToDate,
						superAdminDashboardDto.getOrganizationId(),null,candidateStatusCountDtoList,null);
				svcSearchResult.setData(superAdminDashboardDtoObj);
				svcSearchResult.setOutcome(true);
				svcSearchResult.setMessage(messageSource.getMessage("msg.success", null, LocaleContextHolder.getLocale()));
			}else {
				svcSearchResult.setData(null);
				svcSearchResult.setOutcome(false);
				svcSearchResult.setMessage("Please Specify Organization");
			}
		}
		catch(Exception ex)
		{
			log.error("Exception occured in getPendingDetails method in SuperAdminDashboardServiceImpl-->",ex);
			svcSearchResult.setData(null);
			svcSearchResult.setOutcome(false);
			svcSearchResult.setMessage(messageSource.getMessage("ERROR.MESSAGE", null, LocaleContextHolder.getLocale()));
		}
		return svcSearchResult;
	}

	@Override
	public  ServiceOutcome<SuperAdminDashboardDto> getActivityDetails(SuperAdminDashboardDto superAdminDashboardDto) {
		ServiceOutcome<SuperAdminDashboardDto> svcSearchResult = new ServiceOutcome<SuperAdminDashboardDto>();
		List<CandidateStatusCountDto> candidateStatusCountDtoList=new ArrayList<CandidateStatusCountDto>();
		String strToDate="";
		String strFromDate="";
		try {
			if(superAdminDashboardDto.getOrganizationId()!=null) {
				strToDate = superAdminDashboardDto.getToDate() != null?superAdminDashboardDto.getToDate():ApplicationDateUtils.getStringTodayAsDDMMYYYY();
				strFromDate = superAdminDashboardDto.getFromDate() != null?superAdminDashboardDto.getFromDate():ApplicationDateUtils.subtractNoOfDaysFromDateAsDDMMYYYY(new SimpleDateFormat("dd/MM/yyyy").parse(strToDate),7);
				Date startDate = format.parse(strFromDate+" 00:00:00");
				Date endDate = format.parse(strToDate+" 23:59:59");
				StringBuilder query = new StringBuilder();
				query.append("select newupload,invalid,reinvites,interReport,finalReport,cancelled,invExpired,pendingNow\n");
				query.append("from \n");
				query.append("(select count(*) as newupload from t_dgv_candidate_status_history tdcsh\n");
				query.append("join t_dgv_candidate_basic bas on bas.candidate_id = tdcsh.candidate_id \n");
				query.append("join t_dgv_organization_master org on bas.organization_id = org.organization_id \n");
				query.append("join t_dgv_status_master mas on mas.status_master_id = tdcsh.status_master_id \n");
				query.append("where mas.status_code IN ('NEWUPLOAD','INVALIDUPLOAD')\n");
				if(superAdminDashboardDto.getOrganizationId()!=0) {
					query.append("and org.organization_id =:orgId\n");
				}
				query.append("and tdcsh.candidate_status_change_timestamp between :startDate and :endDate) ne,\n");
				query.append("(select count(*) as invalid from t_dgv_candidate_status_history tdcsh\n");
				query.append("join t_dgv_candidate_basic bas on bas.candidate_id = tdcsh.candidate_id \n");
				query.append("join t_dgv_organization_master org on bas.organization_id = org.organization_id \n");
				query.append("join t_dgv_status_master mas on mas.status_master_id = tdcsh.status_master_id \n");
				query.append("where mas.status_code IN ('INVALIDUPLOAD')\n");
				if(superAdminDashboardDto.getOrganizationId()!=0) {
					query.append("and org.organization_id =:orgId\n");
				}
				query.append("and tdcsh.candidate_status_change_timestamp between :startDate and :endDate) ie,\n");
				query.append("(select count(*) as reinvites from t_dgv_candidate_status_history tdcsh\n");
				query.append("join t_dgv_candidate_basic bas on bas.candidate_id = tdcsh.candidate_id \n");
				query.append("join t_dgv_organization_master org on bas.organization_id = org.organization_id \n");
				query.append("join t_dgv_status_master mas on mas.status_master_id = tdcsh.status_master_id \n");
				query.append("where mas.status_code ='REINVITE'\n");
				if(superAdminDashboardDto.getOrganizationId()!=0) {
					query.append("and org.organization_id =:orgId\n");
				}
				query.append("and tdcsh.candidate_status_change_timestamp between :startDate and :endDate) re,\n");
				query.append("(select count(*) as interReport from t_dgv_candidate_status_history tdcsh\n");
				query.append("join t_dgv_candidate_basic bas on bas.candidate_id = tdcsh.candidate_id \n");
				query.append("join t_dgv_organization_master org on bas.organization_id = org.organization_id \n");
				query.append("join t_dgv_status_master mas on mas.status_master_id = tdcsh.status_master_id \n");
				query.append("where mas.status_code ='PENDINGAPPROVAL'\n");
				if(superAdminDashboardDto.getOrganizationId()!=0) {
					query.append("and org.organization_id =:orgId\n");
				}
				query.append("and tdcsh.candidate_status_change_timestamp between :startDate and :endDate) ir,\n");
				query.append("(select count(*) as finalReport from t_dgv_candidate_status_history tdcsh \n");
				query.append("join t_dgv_candidate_basic bas on bas.candidate_id = tdcsh.candidate_id \n");
				query.append("join t_dgv_organization_master org on bas.organization_id = org.organization_id \n");
				query.append("join t_dgv_status_master mas on mas.status_master_id = tdcsh.status_master_id \n");
				query.append("where mas.status_code ='FINALREPORT'\n");
				if(superAdminDashboardDto.getOrganizationId()!=0) {
					query.append("and org.organization_id =:orgId\n");
				}
				query.append("and tdcsh.candidate_status_change_timestamp between :startDate and :endDate) fr,\n");
				query.append("(select count(*) as cancelled from t_dgv_candidate_status_history tdcsh\n");
				query.append("join t_dgv_candidate_basic bas on bas.candidate_id = tdcsh.candidate_id \n");
				query.append("join t_dgv_organization_master org on bas.organization_id = org.organization_id \n");
				query.append("join t_dgv_status_master mas on mas.status_master_id = tdcsh.status_master_id \n");
				query.append("where mas.status_code ='CANCELLED'\n");
				if(superAdminDashboardDto.getOrganizationId()!=0) {
					query.append("and org.organization_id =:orgId\n");
				}
				query.append("and tdcsh.candidate_status_change_timestamp between :startDate and :endDate) can,\n");
				query.append("(select count(*) as invExpired from t_dgv_candidate_status_history tdcsh\n");
				query.append("join t_dgv_candidate_basic bas on bas.candidate_id = tdcsh.candidate_id \n");
				query.append("join t_dgv_organization_master org on bas.organization_id = org.organization_id \n");
				query.append("join t_dgv_status_master mas on mas.status_master_id = tdcsh.status_master_id \n");
				query.append("where mas.status_code ='INVITATIONEXPIRED'\n");
				if(superAdminDashboardDto.getOrganizationId()!=0) {
					query.append("and org.organization_id =:orgId\n");
				}
				query.append("and tdcsh.candidate_status_change_timestamp between :startDate and :endDate) inve,\n");
				query.append("(select count(*) as pendingNow from t_dgv_candidate_status tds\n");
				query.append("join t_dgv_candidate_basic bas on bas.candidate_id = tds.candidate_id \n");
				query.append("join t_dgv_organization_master org on bas.organization_id = org.organization_id \n");
				query.append("join t_dgv_status_master mas on mas.status_master_id = tds.status_master_id \n");
				query.append("where mas.status_code in('INVITATIONSENT','ITR','EPFO','DIGILOCKER','RELATIVEADDRESS','PENDINGAPPROVAL')\n");
				if(superAdminDashboardDto.getOrganizationId()!=0) {
					query.append("and org.organization_id =:orgId\n");
				}
				query.append("and tds.last_updated_on between :startDate and :endDate) pd\n");
				
				Query resultQuery = entityManager.createNativeQuery(query.toString());
				resultQuery.setParameter("startDate", startDate);
				resultQuery.setParameter("endDate", endDate);
				if(superAdminDashboardDto.getOrganizationId()!=0) {
					resultQuery.setParameter("orgId", superAdminDashboardDto.getOrganizationId());
				}
				
				
				
				List<Object[]> activityList = resultQuery.getResultList();
				for(Object[] activity : activityList) {
					candidateStatusCountDtoList.add(0, new CandidateStatusCountDto("New Upload","NEWUPLOAD",Integer.parseInt(activity[0].toString())));
					candidateStatusCountDtoList.add(1, new CandidateStatusCountDto("Invalid Upload","INVALIDUPLOAD",Integer.parseInt(activity[1].toString())));
					candidateStatusCountDtoList.add(2, new CandidateStatusCountDto("Re-Invites","REINVITE",Integer.parseInt(activity[2].toString())));
					candidateStatusCountDtoList.add(3, new CandidateStatusCountDto("Interim Report","PENDINGAPPROVAL",Integer.parseInt(activity[3].toString())));
					candidateStatusCountDtoList.add(4, new CandidateStatusCountDto("Final Report","FINALREPORT",Integer.parseInt(activity[4].toString())));
					candidateStatusCountDtoList.add(5, new CandidateStatusCountDto("Cancelled","CANCELLED",Integer.parseInt(activity[5].toString())));
					candidateStatusCountDtoList.add(6, new CandidateStatusCountDto("Invitations Expired","INVITATIONEXPIRED",Integer.parseInt(activity[6].toString())));
					candidateStatusCountDtoList.add(7, new CandidateStatusCountDto("Pending Now","PENDINGNOW",Integer.parseInt(activity[7].toString())));
				}
				SuperAdminDashboardDto superAdminDashboardDtoObj=new SuperAdminDashboardDto(strFromDate,strToDate,
						superAdminDashboardDto.getOrganizationId(),null,candidateStatusCountDtoList,null);
				svcSearchResult.setData(superAdminDashboardDtoObj);
				svcSearchResult.setOutcome(true);
				svcSearchResult.setMessage(messageSource.getMessage("msg.success", null, LocaleContextHolder.getLocale()));
			}else {
				svcSearchResult.setData(null);
				svcSearchResult.setOutcome(false);
				svcSearchResult.setMessage("Please Specify Organization");
			}
		}
		catch(Exception ex)
		{
			log.error("Exception occured in getActivityDetails method in SuperAdminDashboardServiceImpl-->",ex);
			svcSearchResult.setData(null);
			svcSearchResult.setOutcome(false);
			svcSearchResult.setMessage(messageSource.getMessage("ERROR.MESSAGE", null, LocaleContextHolder.getLocale()));
		}
		return svcSearchResult;
	}

	@Override
	public ServiceOutcome<SuperAdminDashboardDto> getUtilizationRatePerItem(SuperAdminDashboardDto superAdminDashboardDto) {
		ServiceOutcome<SuperAdminDashboardDto> svcSearchResult = new ServiceOutcome<SuperAdminDashboardDto>();
		List<ServiceConfigdashboardDto> configList=new ArrayList<ServiceConfigdashboardDto>();
		String strToDate="";
		String strFromDate="";
		try {
			if(superAdminDashboardDto.getSourceId()!=null) {
				strToDate = superAdminDashboardDto.getToDate() != null?superAdminDashboardDto.getToDate():ApplicationDateUtils.getStringTodayAsDDMMYYYY();
				strFromDate = superAdminDashboardDto.getFromDate() != null?superAdminDashboardDto.getFromDate():ApplicationDateUtils.subtractNoOfDaysFromDateAsDDMMYYYY(new SimpleDateFormat("dd/MM/yyyy").parse(strToDate),7);
				Date startDate = format.parse(strFromDate+" 00:00:00");
				Date endDate = format.parse(strToDate+" 23:59:59");
				StringBuilder query = new StringBuilder();
				query.append("select sm.organization_id,org.organization_name ,sum(sm.rate_per_item) as rateperitem \n");
				query.append("from t_dgv_service_master sm\n");
				query.append("join t_dgv_organization_master org on org.organization_id = sm.organization_id\n");
				if(superAdminDashboardDto.getSourceId()!=0) {
				query.append("join t_dgv_source src on src.source_id = sm.source_id\n");
				query.append("and sm.source_id =:sourceId \n");
				}
				//query.append("where org.is_active =true group by sm.organization_id,org.organization_name\n");
				query.append("where org.is_active =true ");
				query.append("and COALESCE(sm.last_updated_on ,sm.created_on ) between :startDate and :endDate ");
				query.append("group by sm.organization_id,org.organization_name ");
				Query result = entityManager.createNativeQuery(query.toString()); 
				if(superAdminDashboardDto.getSourceId()!=0) {
					 
					result.setParameter("sourceId", superAdminDashboardDto.getSourceId());
				}
				result.setParameter("startDate", startDate);
				result.setParameter("endDate", endDate);
				
				List<Object[]> configLists = result.getResultList();
				configList = configLists.stream().map(ServiceConfigdashboardDto::new).collect(Collectors.toList());
				SuperAdminDashboardDto superAdminDashboardDtoObj=new SuperAdminDashboardDto(strFromDate,strToDate,
						null,superAdminDashboardDto.getSourceId(),null,configList);
			svcSearchResult.setData(superAdminDashboardDtoObj);
			svcSearchResult.setOutcome(true);
			svcSearchResult.setMessage(messageSource.getMessage("msg.success", null, LocaleContextHolder.getLocale()));
			}else {
				svcSearchResult.setData(null);
				svcSearchResult.setOutcome(false);
				svcSearchResult.setMessage("Please Specify Source");
			}
		}
		catch(Exception ex)
		{
			log.error("Exception occured in getUtilizationRatePerItem method in SuperAdminDashboardServiceImpl-->",ex);
			svcSearchResult.setData(null);
			svcSearchResult.setOutcome(false);
			svcSearchResult.setMessage(messageSource.getMessage("ERROR.MESSAGE", null, LocaleContextHolder.getLocale()));
		}
		return svcSearchResult;
		
	}

	@Override
	public ServiceOutcome<SuperAdminDashboardDto> getUtilizationRatePerReport(SuperAdminDashboardDto superAdminDashboardDto) {
		ServiceOutcome<SuperAdminDashboardDto> svcSearchResult = new ServiceOutcome<SuperAdminDashboardDto>();
		List<ServiceConfigdashboardDto> configList=new ArrayList<ServiceConfigdashboardDto>();
		String strToDate="";
		String strFromDate="";
		try {
			if(superAdminDashboardDto.getSourceId()!=null) {
				strToDate = superAdminDashboardDto.getToDate() != null?superAdminDashboardDto.getToDate():ApplicationDateUtils.getStringTodayAsDDMMYYYY();
				strFromDate = superAdminDashboardDto.getFromDate() != null?superAdminDashboardDto.getFromDate():ApplicationDateUtils.subtractNoOfDaysFromDateAsDDMMYYYY(new SimpleDateFormat("dd/MM/yyyy").parse(strToDate),7);
				Date startDate = format.parse(strFromDate+" 00:00:00");
				Date endDate = format.parse(strToDate+" 23:59:59");
				StringBuilder query = new StringBuilder();
				query.append("select sm.organization_id,org.organization_name ,sum(sm.rate_per_report) as rateperreport \n");
				query.append("from t_dgv_service_master sm\n");
				query.append("join t_dgv_organization_master org on org.organization_id = sm.organization_id\n");
				if(superAdminDashboardDto.getSourceId()!=0) {
				query.append("join t_dgv_source src on src.source_id = sm.source_id\n");
				query.append("and sm.source_id =:sourceId \n");
				}
				//query.append("where org.is_active =true group by sm.organization_id,org.organization_name\n");
				query.append("where org.is_active =true ");
				query.append("and COALESCE(sm.last_updated_on ,sm.created_on ) between :startDate and :endDate ");
				query.append("group by sm.organization_id,org.organization_name ");
				Query result = entityManager.createNativeQuery(query.toString()); 
				if(superAdminDashboardDto.getSourceId()!=0) {
					 
					result.setParameter("sourceId", superAdminDashboardDto.getSourceId());
				}
				result.setParameter("startDate", startDate);
				result.setParameter("endDate", endDate);
				List<Object[]> configLists = result.getResultList();
				configList = configLists.stream().map(ServiceConfigdashboardDto::new).collect(Collectors.toList());
				SuperAdminDashboardDto superAdminDashboardDtoObj=new SuperAdminDashboardDto(strFromDate,strToDate,
						null,superAdminDashboardDto.getSourceId(),null,configList);
			svcSearchResult.setData(superAdminDashboardDtoObj);
			svcSearchResult.setOutcome(true);
			svcSearchResult.setMessage(messageSource.getMessage("msg.success", null, LocaleContextHolder.getLocale()));
		}else {
				svcSearchResult.setData(null);
				svcSearchResult.setOutcome(false);
				svcSearchResult.setMessage("Please Specify Source");
			}
		}
		catch(Exception ex)
		{
			log.error("Exception occured in getUtilizationRatePerReport method in SuperAdminDashboardServiceImpl-->",ex);
			svcSearchResult.setData(null);
			svcSearchResult.setOutcome(false);
			svcSearchResult.setMessage(messageSource.getMessage("ERROR.MESSAGE", null, LocaleContextHolder.getLocale()));
		}
		return svcSearchResult;
	}

	@Override
	public ServiceOutcome<DashboardDto> getCompanyCountByActivity(DashboardDto dashboardDto) {
		ServiceOutcome<DashboardDto> svcSearchResult = new ServiceOutcome<DashboardDto>();
		List<CandidateStatusCountDto> candidateStatusCountDtoList=new ArrayList<CandidateStatusCountDto>();
		String strToDate="";
		String strFromDate="";
		List<Long> orgIds=new ArrayList<Long>();
		List<Long> ids=new ArrayList<Long>();
		List<String> activityCodes=new ArrayList<String>();
	
		try {
			ServiceOutcome<List<OrganizationDto>> svcoutcome=organizationServiceImpl.getOrganizationListAfterBilling();
			if(dashboardDto == null) {
				strToDate = ApplicationDateUtils.getStringTodayAsDDMMYYYY();
				strFromDate = ApplicationDateUtils.subtractNoOfDaysFromDateAsDDMMYYYY(new SimpleDateFormat("dd/MM/yyyy").parse(strToDate),7);
				orgIds= svcoutcome.getData().parallelStream().map(x -> x.getOrganizationId()).collect(Collectors.toList());
				activityCodes.add(0, "NEWUPLOAD");
				dashboardDto=new DashboardDto();
				dashboardDto.setActivityCode(activityCodes);
				ids.add(0, 0l);
				dashboardDto.setOrganizationIds(ids);
			}
			else {
				strToDate=dashboardDto.getToDate();
				strFromDate=dashboardDto.getFromDate();
				if(dashboardDto.getOrganizationIds()!=null && !dashboardDto.getOrganizationIds().isEmpty() ){
					if(dashboardDto.getOrganizationIds().get(0)!=0l) {
						orgIds.addAll(dashboardDto.getOrganizationIds());
					}
					else {
						orgIds.addAll(svcoutcome.getData().parallelStream().map(x -> x.getOrganizationId()).collect(Collectors.toList()));
					}
				}
				if(dashboardDto.getActivityCode()!=null && !dashboardDto.getActivityCode().isEmpty() ) {
					if(!dashboardDto.getActivityCode().get(0).equals("ALL")) {
						activityCodes.addAll(dashboardDto.getActivityCode());
					}else {
						activityCodes.addAll(statusMasterRepository.findAll().parallelStream().map(x->x.getStatusCode()).collect(Collectors.toList()));
					}
				}
			}
			Date startDate = format.parse(strFromDate+" 00:00:00");
			Date endDate = format.parse(strToDate+" 23:59:59");
			List<CandidateStatusHistory> candidateStatusList = new ArrayList<CandidateStatusHistory>();
			for (Long orgid : orgIds) {
				for (String statusCode : activityCodes) {
					List<String> statusList = new ArrayList<>();
					if(statusCode.equals("PENDINGAPPROVAL") || statusCode.equals("INTERIMREPORT")) {
						Collections.addAll(statusList, "PENDINGAPPROVAL");
					}else {
						Collections.addAll(statusList, statusCode);
					}
					candidateStatusList = candidateStatusHistoryRepository.findAllByOrganizationIdAndDateRangeAndStatusCode(orgid,startDate,endDate,statusList);
					if(candidateStatusList != null) {
						StatusMaster invStatusMaster = statusMasterRepository.findByStatusCode(statusCode);
						Organization organization=organizationRepository.findById(orgid).get();
						candidateStatusCountDtoList.add(new CandidateStatusCountDto(invStatusMaster.getStatusName(),invStatusMaster.getStatusCode(),candidateStatusList.size(),organization.getOrganizationId(),organization.getOrganizationName()));
					}
				}
				
			}
			List<CandidateStatusCountDto> sortedList = candidateStatusCountDtoList.parallelStream()
	                .sorted((o1,o2) -> o1.getOrganizationId().compareTo(o2.getOrganizationId()))
	                .collect(Collectors.toList());
			DashboardDto dashboardDtoObj=new DashboardDto(strFromDate,strToDate,
					dashboardDto.getOrganizationIds(),dashboardDto.getActivityCode(),sortedList,null,null,null);
			
			svcSearchResult.setData(dashboardDtoObj);
			svcSearchResult.setOutcome(true);
			svcSearchResult.setMessage(messageSource.getMessage("msg.success", null, LocaleContextHolder.getLocale()));
		}
		catch(Exception ex)
		{
			log.error("Exception occured in getCompanyCountByActivity method in SuperAdminDashboardServiceImpl-->",ex);
			svcSearchResult.setData(null);
			svcSearchResult.setOutcome(false);
			svcSearchResult.setMessage(messageSource.getMessage("ERROR.MESSAGE", null, LocaleContextHolder.getLocale()));
		}
		return svcSearchResult;
	}

}
