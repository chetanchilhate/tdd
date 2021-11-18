package com.medline.tdd.day2;

public class RegistrationService {

  private final NotifierFactory notifierFactory;

  public RegistrationService(NotifierFactory notifierFactory) {
    this.notifierFactory = notifierFactory;
  }

  public void registerUser(User user) {
    notifierFactory.getNotifier(user.getNotificationType())
                   .sendNotification(user.getNotificationMediumDetails(), "Thank you for registration");
  }



}
