package org.company.rpc.server;

import org.company.rpc.client.shared.MyRemoteService;
import org.jboss.errai.bus.server.annotations.Service;

@Service
public class MyRemoteServiceImp implements MyRemoteService {
  @Override
  public String myMethod(String s) {
    String S = s + s;
    return S;
  }
}
