package com.medline.tdd.day2;

import static com.medline.tdd.day2.NotificationType.SMS;

public class User {

  private final String email;
  private final String phone;
  private final NotificationType notificationType;


  public User(String email, String phone, NotificationType notificationType) {
    this.email = email;
    this.phone = phone;
    this.notificationType = notificationType;
  }

  public NotificationType getNotificationType() {
    return notificationType;
  }

  public String getNotificationMediumDetails() {
    return SMS.equals(notificationType) ? phone : email;
  }

}
