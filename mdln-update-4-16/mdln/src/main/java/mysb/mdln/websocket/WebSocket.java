package mysb.mdln.websocket;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import mysb.mdln.domain.Message;

@Component
@ServerEndpoint("/webSocket/{userid}")
public class WebSocket {
	private Session session;

	private static ConcurrentHashMap<String, WebSocket> userWebsocket = new ConcurrentHashMap<>();
	
    private Message messageVO = new Message();

    @OnOpen
    public void onOpen(@PathParam("userid") String userid,Session session) {
        this.session = session;
   
        userWebsocket.put(userid, this);

        messageVO.setType(1);
        messageVO.setNum(userWebsocket.size());
        messageVO.setMsg("123|||||||");

        ObjectMapper mapper = new ObjectMapper();

        String Json = "";
        try {
            Json = mapper.writeValueAsString(messageVO);
        } catch (Exception ex) {
        	System.out.println("1234567123456765434354656879809");
        }

        try {
			this.session.getBasicRemote().sendText(Json);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }


    @OnClose
    public void onClose() {
    	userWebsocket.remove(this);
        messageVO.setType(2);
        messageVO.setNum(userWebsocket.size());
        messageVO.setMsg("有用户离开");

        ObjectMapper mapper = new ObjectMapper();

        String Json = "";
        try {
            Json = mapper.writeValueAsString(messageVO);
        } catch (Exception ex) {
        	System.out.println("1234567??????????????????????????????");
        }
        
        try {
			this.session.getBasicRemote().sendText(Json);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @OnMessage
    public void onMessage(String message) {

    	JSONObject jsonObject = JSON.parseObject(message);
    	String chatto = jsonObject.getString("chatto");
    	String mString = jsonObject.getString("message");
    	WebSocket webSocket = userWebsocket.get(chatto);

    	
    	
        /*ObjectMapper mapper = new ObjectMapper();

        String Json = "";
        try {
            Json = mapper.writeValueAsString(messageVO);
        } catch (Exception ex) {
        	System.out.println("1234567");
        }*/
        this.sendMessage(this,mString);

    }

    public void sendMessage(WebSocket webSocket,String message) {
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

}
