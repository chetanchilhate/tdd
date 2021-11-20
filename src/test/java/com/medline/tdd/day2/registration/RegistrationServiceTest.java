package com.medline.tdd.day2.registration;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RegistrationServiceTest {

  private static final String THANOS_EMAIL = "thanos@avengers.com";
  private static final String THANOS_PHONE_NUM = "5656564444";
  private static final String WELCOME_MESSAGE = "Thank you for registration";

  @Test
  @DisplayName("user should receive email notification when user register with email notification type")
  void userShouldReceiveEmailNotificationOnRegistration() {

    //arrange
    NotifierFactory notifierFactory = mock(NotifierFactory.class);
    RegistrationService registrationService = new RegistrationService(notifierFactory);

    User thanos = new User(THANOS_EMAIL, THANOS_PHONE_NUM, NotificationType.EMAIL);

    Notifier emailNotifier = mock(Notifier.class);
    when(notifierFactory.getNotifier(thanos.getNotificationType())).thenReturn(emailNotifier);

    //act
    registrationService.registerUser(thanos);

    //assert
    verify(emailNotifier, times(1)).sendNotification(THANOS_EMAIL, WELCOME_MESSAGE);
  }

  @Test
  @DisplayName("user should receive email notification when user register with email notification type v2")
  void userShouldReceiveEmailNotificationOnRegistration_v2() {

    //arrange
    NotifierFactory notifierFactory = mock(NotifierFactory.class);
    RegistrationService registrationService = new RegistrationService(notifierFactory);

    User thanos = new User(THANOS_EMAIL, THANOS_PHONE_NUM, NotificationType.EMAIL);

    NotifierStub emailNotifierStub = new NotifierStub();
    when(notifierFactory.getNotifier(thanos.getNotificationType())).thenReturn(emailNotifierStub);

    //act
    registrationService.registerUser(thanos);

    //assert
    assertTrue(emailNotifierStub.hasReceivedMediumDetails(THANOS_EMAIL));
    assertTrue(emailNotifierStub.hasReceivedMessage(WELCOME_MESSAGE));
  }

  @Test
  @DisplayName("user should receive sms notification when user register with sms notification type")
  void userShouldReceiveSmsNotificationNotificationOnRegistration() {

    //arrange
    NotifierFactory notifierFactory = mock(NotifierFactory.class);
    RegistrationService registrationService = new RegistrationService(notifierFactory);

    User thanos = new User(THANOS_EMAIL, THANOS_PHONE_NUM, NotificationType.SMS);

    Notifier smsNotifier = mock(Notifier.class);
    when(notifierFactory.getNotifier(thanos.getNotificationType())).thenReturn(smsNotifier);

    //act
    registrationService.registerUser(thanos);

    //assert
    verify(smsNotifier, times(1)).sendNotification(THANOS_PHONE_NUM, WELCOME_MESSAGE);
  }

}