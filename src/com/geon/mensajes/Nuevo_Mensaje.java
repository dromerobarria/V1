package com.geon.mensajes;

import java.util.ArrayList;
import java.util.Random;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.util.StringUtils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.AsyncTask.Status;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnKeyListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.geon.InspectoresAndroid.R;
import com.geon.InspectoresAndroid.MainActivityPrincipal.OnBackPress;
import com.geon.login.login;


import de.svenjacobs.loremipsum.LoremIpsum;

public class Nuevo_Mensaje  extends Fragment implements OnBackPress{
	
	private com.geon.mensajes.DiscussArrayAdapter adapter;
	private ListView lv;
	private LoremIpsum ipsum;
	private EditText editText1;
	private static Random random;
	
	//xmpp
	private ArrayList<String> messages = new ArrayList();
	private Handler mHandler = new Handler();
	private ListView mList;
	XMPPConnection connection;
	String para;
	String service;
	String username;
	
	SharedPreferences sharedpreferences;
	
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_discuss,
				container, false);
		return view;
  }
	
	
	public void onActivityCreated(Bundle savedInstanceState) {
		
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		sharedpreferences = getActivity().getSharedPreferences(login.MyPREFERENCES,
				Context.MODE_PRIVATE);
		
		//XMPP
		String host = getString(R.string.url_xmpp);
		String port = "5222";// getText(R.id.port);
		service = "geon.cl";// getText(R.id.service);
		username = sharedpreferences.getString("rut_usuario","");
		final String password = sharedpreferences.getString("pass_usuario","");;// getText(R.id.password);
		Log.i("URL",host);
		Log.i("USER",username);
		Log.i("PASS",password);
		ConnectionConfiguration connConfig ;
		connConfig = new ConnectionConfiguration(host, Integer.parseInt(port));
		connConfig.setSASLAuthenticationEnabled(false);
		connection = new XMPPConnection(connConfig);
		
		para = getArguments().getString("para");
		
		
		connection.addConnectionListener(new ConnectionListener() {
			@Override
			public void reconnectionSuccessful() {
				// TODO Auto-generated method stub
				
				
			}
			
			@Override
			public void reconnectionFailed(Exception arg0) {
				// TODO Auto-generated method stub
				Log.i("XMPPClient", "[SettingsDialog] Connected failed");
				
			}
			
			@Override
			public void reconnectingIn(int arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void connectionClosedOnError(Exception arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void connectionClosed() {
				// TODO Auto-generated method stub
				
			}
		});
		
		new Thread(new Runnable() {
			public void run() {
				try {
					connection.connect();
					Log.i("XMPPClient", "[SettingsDialog] Connected to "
							+ connection.getHost());
					connection.addPacketListener(new PacketListener() {
						
						@Override
						public void processPacket(Packet arg0) {
							// TODO Auto-generated method stub
							Log.i("XMPPClient", "[SettingsDialog] Connected in progress");
							
						}
					},new PacketFilter() {
						
						@Override
						public boolean accept(Packet arg0) {
							// TODO Auto-generated method stub
							Log.i("XMPPClient", "[SettingsDialog] Connected result accept");
							return false;
						}
					});
					
					
				} catch (XMPPException ex) {
					Log.e("XMPPClient",
							"[SettingsDialog] Failed to connect to "
									+ connection.getHost());
					Log.e("XMPPClient", ex.toString());
					setConnection(null);
				}
				try {
					connection.login(username, password);
					Log.i("XMPPClient", "Logged in as " + connection.getUser());

					// Set the status to available
					Presence presence = new Presence(Presence.Type.available);
					connection.sendPacket(presence);
					setConnection(connection);
				} catch (XMPPException ex) {
					Log.e("XMPPClient", "[SettingsDialog] Failed to log in as "
							+ username);
					Log.e("XMPPClient", ex.toString());
					setConnection(null);
				}
			}
		}).start();
		
		
		
		
		
		
		
		
		
		
		
		
		random = new Random();
		ipsum = new LoremIpsum();

		lv = (ListView) getActivity().findViewById(R.id.listView1);

		adapter = new DiscussArrayAdapter(getActivity().getApplicationContext(), R.layout.listitem_discuss);

		lv.setAdapter(adapter);

		editText1 = (EditText) getActivity().findViewById(R.id.editText1);
		editText1.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// If the event is a key-down event on the "enter" button
				if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
					// Perform action on key press
					
					
					String  men = editText1.getText().toString();	
					String forpara = para + "@xmpp.geon.cl";
					Message msg  = new Message(forpara, Message.Type.chat);
					msg.setBody(men);
					
					
					connection.sendPacket(msg);
					
					Log.i("XMPPClient", "Sending text [" + men + "] to [" + forpara
							+ "]");
					
					
					adapter.add(new OneComment(false, men));
					editText1.setText("");
						
					
					return true;
				}
				return false;
			}
		});

		addItems();
	}

	
	private void addItems() {
		adapter.add(new OneComment(true, "Hello bubbles!"));

		for (int i = 0; i < 0; i++) {
			boolean left = getRandomInteger(0, 1) == 0 ? true : false;
			int word = getRandomInteger(1, 10);
			int start = getRandomInteger(1, 40);
			String words = ipsum.getWords(word, start);

			adapter.add(new OneComment(left, words));
		}
	}

	private static int getRandomInteger(int aStart, int aEnd) {
		if (aStart > aEnd) {
			throw new IllegalArgumentException("Start cannot exceed End.");
		}
		long range = (long) aEnd - (long) aStart + 1;
		long fraction = (long) (range * random.nextDouble());
		int randomNumber = (int) (fraction + aStart);
		return randomNumber;
	}

	
	@Override
	public void doBack() {
		// TODO Auto-generated method stub
	/*	if(getMensaje.getStatus()==Status.FINISHED){
			
		}
		
		if(getMensaje.getStatus()==Status.RUNNING){
			
			getMensaje.cancel(true);
			
		}
		
		if(getMensaje.getStatus()==Status.PENDING){
			
			getMensaje.cancel(true);
		}*/
		
		
	}
	public void setConnection(XMPPConnection connection) {
		this.connection = connection;
		if (connection != null) {
			// Add a packet listener to get messages sent to us
			PacketFilter filter = new MessageTypeFilter(Message.Type.chat);
			connection.addPacketListener(new PacketListener() {
				public void processPacket(Packet packet) {
					Message message = (Message) packet;
					if (message.getBody() != null) {
						String fromName = StringUtils.parseBareAddress(message
								.getFrom());
						Log.i("XMPPClient", "Got text [" + message.getBody() + "] from [" + fromName + "]");
						
						
						String [] fron = fromName.split("@");
						
					
						if(para.compareTo(fron[0]) == 0){
						
						
						adapter.add(new OneComment(true, message.getBody()));
						
						}
						
						//messages.add(fromName + ":");
						//messages.add(message.getBody());
						// Add the incoming message to the list view
						
					}
				}
			}, filter);
		}
	}

	private void setListAdapter() {
		/*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.multi_line_list_item, messages);
		mList.setAdapter(adapter);*/
	}
}
