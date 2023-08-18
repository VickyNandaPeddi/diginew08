package com.aashdit.digiverifier.config.candidate.util;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.aashdit.digiverifier.config.admin.model.User;
import com.aashdit.digiverifier.config.admin.service.UserService;
import com.aashdit.digiverifier.config.candidate.model.CandidateStatus;
import com.aashdit.digiverifier.config.candidate.service.CandidateService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CandidateInvitationExpired {
	
	@Autowired
	private CandidateService candidateService;
	
	@Autowired
	private UserService userService;
	
	@Scheduled(cron="${com.dgv.candidateSchedularTime}")
	public void expireInvitationForCandidate() {
		List<String> collect=null;
		try {		
			log.info("candidate invitation expired Schedular Started Successfully At " + new Date());	
			List<CandidateStatus> candidateStatusList=candidateService.expireInvitationForCandidate();
			if(!candidateStatusList.isEmpty()) {
				collect = candidateStatusList.stream().map(c -> c.getCandidate().getCandidateCode()).collect(Collectors.toList());
				log.info("Invitation expired for Candidates" + collect);	
			}
		} catch (Exception e) {
		 	log.error("Exception occured in expireInvitationForCandidate method in CandidateInvitationExpired-->",e);
		}
	
	}
	
	@Scheduled(cron="${com.dgv.candidateSchedularTime}")
	public void processDeclined() {
		List<String> collect=null;
		try {		
			log.info("candidate process declined Started Successfully At " + new Date());	
			List<CandidateStatus> candidateStatusList=candidateService.processDeclined();
			if(!candidateStatusList.isEmpty()) {
				collect = candidateStatusList.stream().map(c -> c.getCandidate().getCandidateCode()).collect(Collectors.toList());
				log.info("Invitation expired for Candidates" + collect);	
			}
		} catch (Exception e) {
		 	log.error("Exception occured in processDeclined method in CandidateInvitationExpired-->",e);
		}
	
	}
	
	@Scheduled(cron="${com.dgv.logoutSchedularTime}")
	public void logoutUser() {
		try {		
			userService.logoutUserAfter5Mins();
		} catch (Exception e) {
		 	log.error("Exception occured in logoutUser method in CandidateInvitationExpired-->",e);
		}
	
	}
}
