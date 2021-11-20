package com.medline.tdd.day2.registration;

import static java.util.Objects.isNull;

import java.util.EnumMap;

public class NotifierFactory {

  private static NotifierFactory instance;
  private final EnumMap<NotificationType, Notifier> notifierMap = new EnumMap<>(NotificationType.class);

  private NotifierFactory() {
    notifierMap.put(NotificationType.SMS, new SMSNotifier());
    notifierMap.put(NotificationType.EMAIL, new EmailNotifier());
  }

  public static NotifierFactory createInstance() {
    if(isNull(instance)) {
      instance = new NotifierFactory();
    }
    return instance;
  }

  public Notifier getNotifier(NotificationType notificationType) {
    return notifierMap.get(notificationType);
  }

}
