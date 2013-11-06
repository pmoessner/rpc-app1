package org.company.rpc.client.local;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.company.rpc.client.shared.MyRemoteService;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.EntryPoint;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Main application entry point.
 */
@EntryPoint
public class App {

  private final Label responseLabel = new Label();
  private final Button button = new Button("Send");
  private final TextBox message = new TextBox();

  @Inject
  private Caller<MyRemoteService> remoteService;

  @PostConstruct
  public void init() {

    button.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        String s = message.getText();
        remoteService.call(new RemoteCallback<String>() {
          @Override
          public void callback(String s) {
            responseLabel.setText("Message from Server: " + s);
          }
        }
        ).myMethod(s);
      }
    });

    HorizontalPanel horizontalPanel = new HorizontalPanel();
    horizontalPanel.add(message);
    horizontalPanel.add(button);
    horizontalPanel.add(responseLabel);

    RootPanel.get().add(horizontalPanel);

    System.out.println("UI Constructed!");
}
}