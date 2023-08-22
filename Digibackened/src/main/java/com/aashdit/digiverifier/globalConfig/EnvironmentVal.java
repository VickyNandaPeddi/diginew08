package com.aashdit.digiverifier.globalConfig;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class EnvironmentVal {
    @Value("${aws.access.key}")
    private String accessKey;

    @Value("${aws.secret.key}")
    private String secretKey;

    @Value("${aws.region}")
    private String awsRegion;

    @Value("${mysql.secret.key}")
    private String mysqlSecretKey;

    @Value("${REDIRECT.ANGULAR.AFTER.DIGILOCKER}")
    private String redirectAngularAfterDigiLocker;

    @Value("${REDIRECT.ANGULAR.TO.DIGILOCKER}")
    private String redirectAngularToDigilocker;

    @Value("${REDIRECT.ANGULAR.TO.CANDAPPL}")
    private String redirectAngularToCandAppl;

    @Value("${LETTERAUTHPAGE}")
    private String letterAuthPage;

    @Value("${ISFRESHERPAGE}")
    private String isFreshPage;

    @Value("${RELATIVEBILLPAGE}")
    private String relativeBillPage;

    @Value("${CAFPAGE}")
    private String cafPage;

    @Value("${STATICPAGE}")
    private String staticPage;

    @Value("${UANCONFIRMPAGE}")
    private String uanConfirmPage;

    @Value("${com.dgv.client.redirect.uri}")
    private String dgvClientRedirectUri;

    @Value("${com.dgv.client.access.code.redirect_uri}")
    private String dgvClientAccessCodeRedirectUri;

    @Value("${com.dgv.client.credential.username}")
    private String dgvClientCredentialUserName;

    @Value("${com.dgv.client.credential.password}")
    private String dgvClinetCredentialPassword;

    @Value("${com.dgv.client.relation.credential.username}")
    private String dgvClientRelationCredentialUsername;

    @Value("${com.dgv.client.relation.credential.password}")
    private String dgvClientRelationCredentialPassword;

    @Value("${com.dgv.client.relation.redirect.uri}")
    private String dgvClientRelationRedirectUri;

    @Value("${com.dgv.client.relation.access.code.redirect_uri}")
    private String dgvClientRelationAccessCodeRedirectUri;

    @Value("${com.dgv.client.access.token.url}")
    private String dgvClientAccessTokenUrl;
    @Value("${com.dgv.client.acess.conventional.vendor.username}")
    private String mtUsername;
    @Value("${com.dgv.client.acess.conventional.vendor.password}")
    private String mtPassword;
    @Value("${com.dgv.client.acess.conventional.vendor.granttype}")
    private String mtGrantType;

    @Value("${com.dgv.client.access.code.uri}")
    private String dgvClientAccessCodeUri;
    @Value("${com.dgv.client.acess.conventional.vendor.token}")
    private String conventionalVendorToken;
    @Value("${com.dgv.client.acess.conventional.vendor.fetchvendorchecks}")
    private String conventionalVendorFetchVendorChecks;
    @Value("${com.dgv.client.acess.conventional.vendor.fetchvendorrequestdetails}")
    private String conventionalVendorFetchVendorRequestDetails;

    @Value("${com.dgv.client.response.type}")
    private String dgvClientResponseType;

    @Value("${com.dgv.client.access.user.detail.api}")
    private String dgvClientAccessUserDetailApi;

    @Value("${com.dgv.client.access.user.files.issued.api}")
    private String dgvClientAccessUserFilesIssuedApi;

    @Value("${com.dgv.client.access.user.file.from.uri.api}")
    private String dgvClientAccessUserFileFromUriApi;

    @Value("${com.dgv.client.access.user.file.from.uri.pdf}")
    private String dgvClientAccessUserFileFromUriPdf;

    @Value("${com.dgv.client.credential.epfo.clientid}")
    private String dgvClientCredentialEpfoClientId;

    @Value("${com.dgv.client.credential.epfo.clientsecret}")
    private String dgvClientCredentialEpfoClientSecret;

    @Value("${com.dgv.client.access.epfo.token.url}")
    private String dgvClientCredentialEpfoTokenUrl;

    @Value("${com.dgv.client.access.epfo.transactionid.url}")
    private String dgvClientCredentialEpfoTransactionIdUrl;

    @Value("${com.dgv.client.credential.epfo.clientid.value}")
    private String dgvClientCredentialEpfoClientValue;

    @Value("${com.dgv.client.credential.epfo.clientid.secret.value}")
    private String dgvClientCredentialEpfoClientIdSecretValue;

    @Value("${com.dgv.client.access.epfo.post.loginpage.session.url}")
    private String dgvClientAccessEpfoPostLoginPageSessionUrl;

    @Value("${com.dgv.client.access.epfo.post.submit.post.url")
    private String dgvClientAccessEpfoPostSubmitPostUrl;

    @Value("${com.dgv.client.access.epfo.captcha.image.path}")
    private String dgvClientAccessEpfoCaptchaImagePath;

    @Value("${com.dgv.client.credential.itr.clientid}")
    private String dgvClientCredentialItrClientId;

    @Value("${com.dgv.client.credential.itr.clientsecret}")
    private String dgvClientCredentialItrClientSecret;

    @Value("${com.dgv.client.access.itr.token.url}")
    private String dgvClientAccessItrTokenUrl;

    @Value("${com.dgv.client.access.itr.transactionid.url}")
    private String dgvClientAccessItrTransactionIdUrl;

    @Value("${com.dgv.client.credential.itr.clientid.value}")
    private String dgvClientCredentialItrClientIdValue;

    @Value("${com.dgv.client.credential.itr.clientid.secret.value}")
    private String dgvClientAccessItrClientSecretValue;

    @Value("${com.dgv.client.access.itr.post.logininfo.url}")
    private String dgvClientAccessItrPostLoginInfoUrl;


}
