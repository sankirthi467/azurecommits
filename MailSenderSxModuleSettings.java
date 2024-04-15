package com.planonsoftware.app.customer.br.settings;

import com.planonsoftware.app.platform.settings.v1.Settings;
import com.planonsoftware.app.platform.settings.v1.attribute.Description;

@Settings("Module Settings")
public interface MailSenderSxModuleSettings
{
   @Description("Message to send the internal tradesperson")
   String getEmailContentMessage();
}