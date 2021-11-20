package com.medline.tdd.day2.registration;

import com.medline.tdd.day2.registration.Notifier;

public class NotifierStub implements Notifier {

  private String notificationMediumDetails;
  private String message;

  @Override
  public void sendNotification(String notificationMediumDetails, String message) {
    this.notificationMediumDetails = notificationMediumDetails;
    this.message = message;
  }

  public boolean hasReceivedMediumDetails(String mediumDetails) {
    return this.notificationMediumDetails.equals(mediumDetails);
  }

  public boolean hasReceivedMessage(String message) {
    return this.message.equals(message);
  }

}
