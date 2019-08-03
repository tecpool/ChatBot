# ChatBot

add it in build.gradle----------------------------------------------------------------

 compile 'com.github.tecpool:ChatBot:1.0.2'
 
 add it in your xml file---------------------------------------------------------------
 
   <in.tecpool.chatbot.ChatBot
        android:layout_width="80dp"
        android:id="@+id/chatbot"
        android:layout_height="80dp"/>
  
 add it in your on create in activity.java file  ----------------------------------------     
        
        ChatBot chatBot=(ChatBot)findViewById(R.id.chatbot);
        chatBot.init(CalActivity.this,"123456789","1");
        
        
 get it from us--------------------------------------------------------------------------
        
        "123456789" -----this is your bot key    --- get from us mail to info.tecpool.com
        "1"--------this is your id          ---------get from us mail to info.tecpool.com
        

 
