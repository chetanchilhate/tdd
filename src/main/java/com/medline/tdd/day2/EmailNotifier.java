package com.medline.tdd.day2;

public class EmailNotifier implements Notifier {
  @Override
  public String getWelcomeMessage(String notificationMediumDetails, String message) {
    return message;
  }
}
