package com.planonsoftware.app.customer.br;

import com.planonsoftware.app.customer.br.settings.MailSenderSxModuleSettings;
import com.planonsoftware.platform.app.v2.ILogService;
import com.planonsoftware.platform.backend.businessrule.v3.IBusinessRule;
import com.planonsoftware.platform.backend.businessrule.v3.IBusinessRuleContext;
import com.planonsoftware.platform.backend.data.v1.IBusinessObject;
import com.planonsoftware.platform.backend.mail.v1.IMailMessage;
import com.planonsoftware.platform.backend.mail.v1.IMailService;


public class MailSenderSx implements IBusinessRule
{

    @Override
    public void execute(IBusinessObject moveOrderNewBO, IBusinessObject moveOrderOldBO, IBusinessRuleContext aContext) {
        MailSenderSxModuleSettings settings = aContext.getAppService().getSettings();
        ILogService logService = aContext.getLogService();
    
        if(moveOrderNewBO.isNew()){
            logService.info("Entry of SX");
           IBusinessObject internalTradesPersonBO = moveOrderNewBO.getReferenceFieldByName("InternalTradesmanPersonRef").getValue();
           if(internalTradesPersonBO !=null){
            logService.info("Internal Trades Person  present");
            String emailAddress = internalTradesPersonBO.getStringFieldByName("Email").getValueAsString();
            IMailService mailService = aContext.getMailService();
            IMailMessage mailMessage = mailService.createMessage();
            mailMessage.setToEmail(emailAddress);
            logService.info(emailAddress);
            String orderNumber = moveOrderNewBO.getFieldByName("OrderNumber").getValueAsString();
            mailMessage.setSubject("Move Order with code"+orderNumber+"is created and assigned.");
            mailMessage.setContent(settings.getEmailContentMessage());
            mailMessage.send();
            logService.info("mail Sent");
           }
        }
        
    }
}