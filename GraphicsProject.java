import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;
import javax.sound.sampled.*;
import java.awt.Toolkit;
import java.net.*;   
import java.lang.Math;
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.UnsupportedAudioFileException; 

public class GraphicsProject 
{
 
  public static void main(String...args) 
  { 
    JFrame j = new JFrame();  
    MyPanel m = new MyPanel();
    j.setSize(m.getSize());
    j.add(m); 
    j.setVisible(true); 
    j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
  }
 
}

class MyPanel extends JPanel implements ActionListener, KeyListener, MouseListener 
{
   
 private Timer time;
 private Timer time2;
 private int timer;
 private int left;
 private int up;
 private int down;
 private int stayLeft;
 private int stayDown;
 private int birdsLeft;
 private int treeLeft;
 private int sunDown;
 static File file; 
 static AudioInputStream stream; 
 static Clip music; 
 
 MyPanel()  
 {
   time = new Timer(15000, this);
   time2 = new Timer(15,this);
     timer = 0;
   setSize(2000, 1000);
   setVisible(true); 
   left = 0;
   up = 0;
   birdsLeft = 0;
   treeLeft = 0;
   setFocusable(true);
   addKeyListener(this);
  addMouseListener(this);
 }
 
 public void paintComponent(Graphics g)
 {
  if(sunDown<1550) //changes from day to night
   drawBackground(g,up);
  else
   drawBackground2(g);
  drawSun(g);
  drawMoon(g);
  drawClouds(g);
  drawRunway(g,left,up);
  drawAirport(g);
  drawTower(g);
  drawTree(g);
  drawAirplane(g);
  drawBirds(g);
    
  if(left==0) //plays "Press 'A'"
    words(g);
  if(left >= 10 && up == 0) //plays "Click mouse"
    words2(g);
  if(timer >= 25000 && timer <= 31000) //plays "Press down"
    words3(g);
  if(timer >= 31000 && up+down <=10) //plays "SUCCESS!!!" and ends the animation
  {
    music.stop();
    playMusic2();
    words4(g);
    time2.stop();
    time.stop();
  }
 }
 
 public void actionPerformed(ActionEvent e)
 { 
  time2.start();
   left+=5; //speed of the plane
   timer+=15;
   if(birdsLeft >= 3000) //replays the birds
    birdsLeft = -1000;
   birdsLeft= birdsLeft + 10; //speed of the birds
   if(treeLeft >= 2000) //moves the tree and replays it
     treeLeft = -500;
   treeLeft+= 5; //speed of the tree
   sunDown += 2; //speed of the sun
   if(sunDown >=3050) //replays the sun and moon
     sunDown = 0;
  repaint();
 }

  public void keyPressed(KeyEvent e) //user starts animation and goes down by pressing down
 {
   if(e.getKeyCode()==KeyEvent.VK_A)
    { 
      time.start();
      playMusic();
    }
    if(e.getKeyCode() == KeyEvent.VK_DOWN)
      down -= 10;
 
    repaint();
  }
 
  public void playMusic() //plays airplane music
  {
   try
    {
     music();
    }
    catch(Exception e){}
  }
 
 public void playMusic2() //plays SUCCESS!!! music
 {
    try
    {
      music2();
    }
   catch(Exception e){}
  }
 
  public void music() throws Exception //airplane music  
  {
    file = new File("airplane2.wav");//File must be .WAV, .AU, or .AIFF
    stream = AudioSystem.getAudioInputStream(file);
    music = AudioSystem.getClip();
    music.open(stream);
    music.start(); //Start the music
    music.loop(Clip.LOOP_CONTINUOUSLY); //Loop the music
    //music.stop(); //Stop the music
   //music.close(); //Unload the clip from memory
  }
 
  public void music2() throws Exception //success music  
 {
  file = new File("kids-cheering-gaming-sound-effect-hd.wav");//File must be .WAV, .AU, or .AIFF
  stream = AudioSystem.getAudioInputStream(file);
  music = AudioSystem.getClip();
   music.open(stream);
   music.start(); //Start the music
    //music.loop(Clip.LOOP_CONTINUOUSLY); //Loop the music
    //music.stop(); //Stop the music
    //music.close(); //Unload the clip from memory
  }
 
  public void keyReleased(KeyEvent e){}
 
  public void keyTyped(KeyEvent e){}
  public void mousePressed(MouseEvent e) // clicks mouse to move plane up
  {
    up=up+10;
    repaint();
  }
  public void mouseReleased(MouseEvent e){}

  public void mouseClicked(MouseEvent e){}

  public void mouseEntered(MouseEvent e){}
 public void mouseExited(MouseEvent e){}
 
  public void drawBackground(Graphics g,int up) //day background
    {
     Color blue = new Color(31,190,214);
      g.setColor(blue);
      g.fillRect(0,0,2000,1500);
      g.setColor(Color.GREEN);
      g.fillRect(0,850+up + down,2000,1000);
    }  
    
    public void drawBackground2(Graphics g) //night background
    {
     g.setColor(Color.BLUE);
     g.fillRect(0,0,2000,1500);
     g.setColor(Color.GREEN);
     g.fillRect(0,850+up+down,2000,1000);
    }
    public void words(Graphics g) //what user presses to start the animation
    {
      g.setColor(Color.RED);
      g.setFont(new Font("TimesRoman", Font.PLAIN, 100)); 
      g.drawString("Press A",600,400);
    }
    
    public void words2(Graphics g) // when user needs to take off
    {
       g.setColor(Color.RED);
       g.setFont(new Font("TimesRoman", Font.PLAIN, 100)); 
       g.drawString("Click the mouse",600,400);
    }
    
    public void words3(Graphics g) //when user is about to land
    {
       g.setColor(Color.RED);
       g.setFont(new Font("TimesRoman", Font.PLAIN, 100)); 
       g.drawString("Press down",600,400);
    }
    
    public void words4(Graphics g) //when user successfully lands
    {
       g.setColor(Color.RED);
       g.setFont(new Font("TimesRoman", Font.PLAIN, 100)); 
       g.drawString("SUCCESS!!!!!",600,400);
    }
    
    public void drawClouds(Graphics g) //clouds
    {
      g.setColor(Color.WHITE);
     
      g.fillOval(30,20,40,40);
     g.fillOval(55,20,40,40);
     g.fillOval(80,20,40,40);
     g.fillOval(30,45,40,40);
     g.fillOval(55,45,40,40);
     g.fillOval(80,45,40,40);
     g.fillOval(90,32,40,40);
     g.fillOval(20,32,40,40);
      
      g.fillOval(30+100,20+320,40,40);
      g.fillOval(55+100,20+320,40,40);
     g.fillOval(80+100,20+320,40,40);
     g.fillOval(30+100,45+320,40,40);
     g.fillOval(55+100,45+320,40,40);
     g.fillOval(80+100,45+320,40,40);
     g.fillOval(90+100,32+320,40,40);
      g.fillOval(20+100,32+320,40,40);
      
      g.fillOval(30+350,20+250,40,40);
      g.fillOval(55+350,20+250,40,40);
      g.fillOval(80+350,20+250,40,40);
      g.fillOval(30+350,45+250,40,40);
      g.fillOval(55+350,45+250,40,40);
      g.fillOval(80+350,45+250,40,40);
      g.fillOval(90+350,32+250,40,40);
      g.fillOval(20+350,32+250,40,40);
      
      
      g.fillOval(30+700,20+200,40,40);
      g.fillOval(55+700,20+200,40,40);
      g.fillOval(80+700,20+200,40,40);
      g.fillOval(30+700,45+200,40,40);
      g.fillOval(55+700,45+200,40,40);
      g.fillOval(80+700,45+200,40,40);
      g.fillOval(90+700,32+200,40,40);
     g.fillOval(20+700,32+200,40,40);
      
      g.fillOval(30+1000,20+400,40,40);
      g.fillOval(55+1000,20+400,40,40);
      g.fillOval(80+1000,20+400,40,40);
      g.fillOval(30+1000,45+400,40,40);
      g.fillOval(55+1000,45+400,40,40);
      g.fillOval(80+1000,45+400,40,40);
      g.fillOval(90+1000,32+400,40,40);
      g.fillOval(20+1000,32+400,40,40);
      
      g.fillOval(30+1500,20+240,40,40);
      g.fillOval(55+1500,20+240,40,40);
      g.fillOval(80+1500,20+240,40,40);
      g.fillOval(30+1500,45+240,40,40);
      g.fillOval(55+1500,45+240,40,40);
      g.fillOval(80+1500,45+240,40,40);
      g.fillOval(90+1500,32+240,40,40);
      g.fillOval(20+1500,32+240,40,40);
      
      g.fillOval(30+2000,20+350,40,40);
      g.fillOval(55+2000,20+350,40,40);
      g.fillOval(80+2000,20+350,40,40);
      g.fillOval(30+2000,45+350,40,40);
      g.fillOval(55+2000,45+350,40,40);
      g.fillOval(80+2000,45+350,40,40);
      g.fillOval(90+2000,32+350,40,40);
      g.fillOval(20+2000,32+350,40,40);
    }
    
    public void drawTree(Graphics g) //tree
    {
     //trunk
     Color Brown = new Color(102,51,0);
     g.setColor(Brown);
     int npoints=48;
     int xpoints [] ={667+300-treeLeft,670+300-treeLeft,675+300-treeLeft,678+300-treeLeft,683+300-treeLeft,684+300-treeLeft,687+300-treeLeft,689+300-treeLeft,690+300-treeLeft,694+300-treeLeft,691+300-treeLeft,683+300-treeLeft,675+300-treeLeft,681+300-treeLeft,689+300-treeLeft,696+300-treeLeft,701+300-treeLeft,707+300-treeLeft,708+300-treeLeft,712+300-treeLeft,717+300-treeLeft,723+300-treeLeft,723+300-treeLeft,729+300-treeLeft,733+300-treeLeft,729+300-treeLeft,727+300-treeLeft,722+300-treeLeft,721+300-treeLeft,723+300-treeLeft,723+300-treeLeft,724+300-treeLeft,727+300-treeLeft,730+300-treeLeft,734+300-treeLeft,746+300-treeLeft,738+300-treeLeft,727+300-treeLeft,721+300-treeLeft,715+300-treeLeft,713+300-treeLeft,713+300-treeLeft,708+300-treeLeft,702+300-treeLeft,695+300-treeLeft,689+300-treeLeft,683+300-treeLeft,676+300-treeLeft };
     int ypoints [] = {236+652+up+down,229+652+up+down,227+652+up+down,221+652+up+down,215+652+up+down,208+652+up+down,197+652+up+down,187+652+up+down,175+652+up+down,162+652+up+down,149+652+up+down,136+652+up+down,131+652+up+down,122+652+up+down,126+652+up+down,126+652+up+down,125+652+up+down,133+652+up+down,137+652+up+down,133+652+up+down,127+652+up+down,125+652+up+down,122+652+up+down,120+652+up+down,125+652+up+down,127+652+up+down,135+652+up+down,145+652+up+down,158+652+up+down,169+652+up+down,175+652+up+down,189+652+up+down,201+652+up+down,209+652+up+down,221+652+up+down,233+652+up+down,235+652+up+down,232+652+up+down,230+652+up+down,229+652+up+down,233+652+up+down,239+652+up+down,235+652+up+down,229+652+up+down,226+652+up+down,231+652+up+down,232+652+up+down,234+652+up+down};
     g.fillPolygon(xpoints,ypoints,npoints);
     //leaves
     g.setColor(Color.GREEN);
     int x1points [] ={625+300-treeLeft,619+300-treeLeft,611+300-treeLeft,605+300-treeLeft,601+300-treeLeft,601+300-treeLeft,602+300-treeLeft,606+300-treeLeft,610+300-treeLeft,616+300-treeLeft,621+300-treeLeft,627+300-treeLeft,633+300-treeLeft,633+300-treeLeft,631+300-treeLeft,631+300-treeLeft,633+300-treeLeft,637+300-treeLeft,642+300-treeLeft,647+300-treeLeft,653+300-treeLeft,659+300-treeLeft,661+300-treeLeft,665+300-treeLeft,669+300-treeLeft,674+300-treeLeft,680+300-treeLeft,687+300-treeLeft,693+300-treeLeft,699+300-treeLeft,705+300-treeLeft,711+300-treeLeft,716+300-treeLeft,719+300-treeLeft,722+300-treeLeft,723+300-treeLeft,724+300-treeLeft,728+300-treeLeft,732+300-treeLeft,737+
      300-treeLeft,742+300-treeLeft,748+300-treeLeft,754+300-treeLeft,760+300-treeLeft,765+300-treeLeft,770+300-treeLeft,775+300-treeLeft,779+300-treeLeft,782+300-treeLeft,785+300-treeLeft,787+300-treeLeft,788+300-treeLeft,788+300-treeLeft,786+300-treeLeft,790+300-treeLeft,796+300-treeLeft,802+300-treeLeft,806+300-treeLeft,808+300-treeLeft,808+300-treeLeft,809+300-treeLeft,807+300-treeLeft,804+300-treeLeft,801+300-treeLeft,796+300-treeLeft,792+300-treeLeft,788+300-treeLeft,787+300-treeLeft,787+300-treeLeft,782+300-treeLeft,779+300-treeLeft,779+300-treeLeft,779+300-treeLeft,774+300-treeLeft,768+300-treeLeft,761+300-treeLeft,755+300-treeLeft,749+300-treeLeft,744+300-treeLeft,739+300-treeLeft,734+300-treeLeft,728+300-treeLeft,723+300-treeLeft,719+300-treeLeft,716+300-treeLeft,713+300-treeLeft,710+300-treeLeft,705+300-treeLeft,702+300-treeLeft,697+300-treeLeft,690+300-treeLeft,684+300-treeLeft,680+300-treeLeft,677+300-treeLeft,672+300-treeLeft,667+300-treeLeft,662+300-treeLeft,658+300-treeLeft,654+300-treeLeft,649+300-treeLeft,642+300-treeLeft,636+300-treeLeft,631+300-treeLeft,627+300-treeLeft,625+300-treeLeft,625+300-treeLeft};
     int y1points [] ={113+652+up+down,113+652+up+down,111+652+up+down,106+652+up+down,98+652+up+down,87+652+up+down,79+652+up+down,73+652+up+down,67+652+up+down,65+652+up+down,64+652+up+down,63+652+up+down,64+652+up+down,57+652+up+down,51+652+up+down,43+652+up+down,36+652+up+down,31+652+up+down,26+652+up+down,23+652+up+down,23+652+up+down,24+652+up+down,17+652+up+down,11+652+up+down,7+652+up+down,3+652+up+down,2+652+up+down,1+652+up+down,1+652+up+down,1+652+up+down,3+652+up+down,6+652+up+down,9+652+up+down,14+652+up+down,20+652+up+down,24+652+up+down,28+652+up+down,25+652+up+down,20+652+up+down,17+652+up+down,14+652+up+down,14+652+up+down,14+652+up+down,15+652+up+down,17+652+up+down,20+652+up+down,23+652+up+down,28+652+up+down,32+652+up+down,37+652+up+down,43+652+up+down,48+652+up+down,52+652+up+down,56+652+up+down,60+652+up+down,63+652+up+down,66+652+up+down,71+652+up+down,76+652+up+down,83+652+up+down,91+652+up+down,98+652+up+down,103+652+up+down,108+652+up+down,112+652+up+down,113+652+up+down,113+652+up+down,116+652+up+down,121+652+up+down,122+652+up+down,121+652+up+down,125+652+up+down,130+652+up+down,133+652+up+down,136+652+up+down,136+652+up+down,136+652+up+down,134+652+up+down,131+652+up+down,127+652+up+down,123+652+up+down,120+652+up+down,115+652+up+down,110+652+up+down,106+652+up+down,109+652+up+down,114+652+up+down,119+652+up+down,124+652+up+down,128+652+up+down,127+652+up+down,125+652+up+down,122+652+up+down,126+652+up+down,130+652+up+down,134+652+up+down,134+652+up+down,131+652+up+down,128+652+up+down,130+652+up+down,132+652+up+down,131+652+up+down,129+652+up+down,125+652+up+down,122+652+up+down,117+652+up+down};
     int n1points = 106;
     g.fillPolygon(x1points,y1points,n1points);
    }
    
    public void drawTower(Graphics g) //watchtower
    {
     //base 
     Color DGRAY = new Color(96,96,96);
     g.setColor(DGRAY);
     int xpoints[] = {276+900-left,333+900-left,329+900-left,329+900-left,353+900-left,353+900-left,259+900-left,259+900-left,283+900-left,283+900-left};
     int ypoints[] = {62+404,62+404,73+404,88+404,88+404,98+404,98+404,88+404,88+404,73+404};
     int npoints = 10;
     g.fillPolygon(xpoints,ypoints,npoints);
     //light 
     Color LBLUE =  new Color(51,255,255);
     g.setColor(LBLUE);
     int x1points[] = {220+900-left,389+900-left,389+900-left,384+900-left,384+900-left,361+900-left,250+900-left,226+900-left,226+900-left,220+900-left};
     int y1points[] = {100+404,100+404,107+404,107+404,120+404,178+404,178+404,120+404,107+404,107+404};
     int n1points = 10;
     g.fillPolygon(x1points,y1points,n1points);
     //roof
     g.setColor(DGRAY);
     int x2points[] = {250+900-left,251+900-left,259+900-left,260+900-left,241+900-left,239+900-left,250+900-left,249+900-left,259+900-left,259+900-left,250+900-left,249+900-left,240+900-left,238+900-left,371+900-left,372+900-left,361+900-left,362+900-left,352+900-left,351+900-left,362+900-left,361+900-left,372+900-left,372+900-left,353+900-left,352+900-left,363+900-left,360+900-left};
     int y2points[] = {178+404,197+404,199+404,242+404,244+404,265+404,267+404,285+404,286+404,443+404,444+404,488+404,490+404,509+404,508+404,491+404,489+404,445+404,445+404,287+404,286+404,268+404,267+404,243+404,244+404,199+404,198+404,179+404};
     int n2points = 28;
     g.fillPolygon(x2points,y2points,n2points);
    }
    
    public void drawRunway(Graphics g,int left,int up) //runway
    {
      //road1 
      g.setColor(Color.GRAY);
      g.fillRect(300-left,900+up+down,2000,1000);
      //lines1
      g.setColor(Color.YELLOW);
      for(int x = 0; x < 16; x++) 
        g.fillRect(350 + x*120 -left,950+up+down,100,50);
      //road2
      g.setColor(Color.GRAY);
      g.fillRect(10000-left,900+up+down,2000,1000);
      //lines2
      g.setColor(Color.YELLOW);
      for(int x = 0; x < 16; x++) 
        g.fillRect(10100 + x*120 -left,950+up+down,100,50);
    }
    
    public void drawAirport(Graphics g) //airport
    {
      //roof 
      g.setColor(Color.BLACK);
      g.fillRect(0-left,550+up+down,200,75);
      g.fillArc(20-left,575+up+down,300,100,0,90);
      //windows
      g.setColor(new Color(135,206,235));
      g.fillRect(0-left,625+up+down,320,100);
      //base
      g.setColor(Color.GRAY);
      g.fillRect(0-left,725+up+down,320,150);
      //window lines
      g.setColor(Color.BLACK);
      g.drawLine(40-left,625+up+down,40-left,725+up+down);
      g.drawLine(80-left,625+up+down,80-left,725+up+down);
      g.drawLine(120-left,625+up+down,120-left,725+up+down);
      g.drawLine(160-left,625+up+down,160-left,725+up+down);
      g.drawLine(200-left,625+up+down,200-left,725+up+down);
      g.drawLine(240-left,625+up+down,240-left,725+up+down);
      g.drawLine(280-left,625+up+down,280-left,725+up+down);
      g.drawLine(320-left,625+up+down,320-left,725+up+down);
    } 
     
    public void drawAirplane(Graphics g) //airplane
    {
      if(left < 500)
         stayLeft = left;
      if(up < 400)
         stayDown = up;
      //body
      g.setColor(Color.WHITE);
      g.fillRect(350+stayLeft,850-stayDown-down,450,90);
      //top head
      Polygon c = new Polygon();
      c.addPoint(800+stayLeft,850-stayDown-down);
      c.addPoint(850+stayLeft,875-stayDown-down);
      c.addPoint(800+stayLeft,875-stayDown-down);
      g.fillPolygon(c);  
      //bottom head                                                                                                                                                                                                
      g.fillArc(725+stayLeft,810-stayDown-down,120,130,270,90);
      //bottom tail
      g.fillArc(270+stayLeft,760-stayDown-down,200,180,180,90);
      //top tail
      Polygon p = new Polygon();
      p.addPoint(350+stayLeft,850-stayDown-down);
      p.addPoint(300+stayLeft,800-stayDown-down);
      p.addPoint(280+stayLeft,700-stayDown-down);
      p.addPoint(260+stayLeft,700-stayDown-down);
      p.addPoint(270+stayLeft,850-stayDown-down);
      g.fillPolygon(p);
      //wing
      Polygon a = new Polygon();
      a.addPoint(700+stayLeft,880-stayDown-down);
      a.addPoint(700+stayLeft,900-stayDown-down);
      a.addPoint(650+stayLeft,920-stayDown-down);
      a.addPoint(400+stayLeft,890-stayDown-down);
      a.addPoint(400+stayLeft,880-stayDown-down);
      g.setColor(Color.BLACK);
      g.drawPolygon(a);
      //wheels
      g.fillOval(500+stayLeft,950-stayDown-down,30,30);
      g.fillOval(750+stayLeft,950-stayDown-down,30,30);
      g.fillRect(513+stayLeft,940-stayDown-down,5,15);
      g.fillRect(763+stayLeft,940-stayDown-down,5,15);
      //engine
      Color y = new Color(200,200,200);
      g.setColor(y);
      g.fillRect(700+stayLeft,900-stayDown-down,75,50);
      Color z = new Color(180,180,180);
      g.setColor(z);
      Polygon b = new Polygon();
      b.addPoint(700+stayLeft,900-stayDown-down);
      b.addPoint(650+stayLeft,920-stayDown-down);
      b.addPoint(650+stayLeft,930-stayDown-down);
      b.addPoint(700+stayLeft,950-stayDown-down);
      g.fillPolygon(b);
      //captain window
      g.setColor(Color.BLACK);
      Polygon d = new Polygon();
      d.addPoint(820+stayLeft,860-stayDown-down);
      d.addPoint(795+stayLeft,860-stayDown-down);
      d.addPoint(793+stayLeft,865-stayDown-down);
      d.addPoint(793+stayLeft,870-stayDown-down);
      d.addPoint(840+stayLeft,870-stayDown-down);
      g.fillPolygon(d);
      //windows
      for(int x = 0; x < 20; x++)
        g.fillRect(350+x*20+stayLeft,865-stayDown-down,10,10);
      //door
      g.drawRect(755+stayLeft,855-stayDown-down,20,40);
    }
    
    public void drawBirds(Graphics g) //birds
    {
      //1st bird
      g.setColor(Color.BLACK);
      int xpoints[] = {60+1406-birdsLeft,64+1406-birdsLeft,68+1406-birdsLeft,72+1406-birdsLeft,75+1406-birdsLeft,80+1406-birdsLeft,85+1406-birdsLeft,90+1406-birdsLeft,92+1406-birdsLeft,94+1406-birdsLeft,97+1406-birdsLeft,101+1406-birdsLeft,106+1406-birdsLeft,108+1406-birdsLeft,113+1406-birdsLeft,116+1406-birdsLeft,117+1406-birdsLeft,113+1406-birdsLeft,110+1406-birdsLeft,107+1406-birdsLeft,105+1406-birdsLeft,103+1406-birdsLeft,101+1406-birdsLeft,103+1406-birdsLeft,107+1406-birdsLeft,110+1406-birdsLeft,114+1406-birdsLeft,117+1406-birdsLeft,120+1406-birdsLeft,118+1406-birdsLeft,114+1406-birdsLeft,110+1406-birdsLeft,106+1406-birdsLeft,102+1406-birdsLeft,99+1406-birdsLeft,98+1406-birdsLeft,96+1406-birdsLeft,95+1406-birdsLeft,92+1406-birdsLeft,92+1406-birdsLeft,93+1406-birdsLeft,90+1406-birdsLeft,89+1406-birdsLeft,89+1406-birdsLeft,88+1406-birdsLeft,88+1406-birdsLeft,85+1406-birdsLeft,81+1406-birdsLeft,81+1406-birdsLeft,81+1406-birdsLeft,79+1406-birdsLeft,80+1406-birdsLeft,81+1406-birdsLeft,82+1406-birdsLeft,84+1406-birdsLeft,81+1406-birdsLeft,78+1406-birdsLeft,75+1406-birdsLeft,70+1406-birdsLeft,64+1406-birdsLeft};
      int ypoints[] = {91,88,88,87,87,87,86,82,76,70,67,66,64,62,61,63,65,67,70,74,77,80,83,85,86,86,85,85,87,90,90,91,92,94,95,96,100,103,108,111,114,116,120,122,125,129,129,126,120,114,110,105,102,97,94,93,93,93,93,93};
      int npoints=60;
      g.fillPolygon(xpoints,ypoints,npoints);
      //2nd bird
      int x1points[] = {60+1306-birdsLeft,64+1306-birdsLeft,68+1306-birdsLeft,72+1306-birdsLeft,75+1306-birdsLeft,80+1306-birdsLeft,85+1306-birdsLeft,90+1306-birdsLeft,92+1306-birdsLeft,94+1306-birdsLeft,97+1306-birdsLeft,101+1306-birdsLeft,106+1306-birdsLeft,108+1306-birdsLeft,113+1306-birdsLeft,116+1306-birdsLeft,117+1306-birdsLeft,113+1306-birdsLeft,110+1306-birdsLeft,107+1306-birdsLeft,105+1306-birdsLeft,103+1306-birdsLeft,101+1306-birdsLeft,103+1306-birdsLeft,107+1306-birdsLeft,110+1306-birdsLeft,114+1306-birdsLeft,117+1306-birdsLeft,120+1306-birdsLeft,118+1306-birdsLeft,114+1306-birdsLeft,110+1306-birdsLeft,106+1306-birdsLeft,102+1306-birdsLeft,99+1306-birdsLeft,98+1306-birdsLeft,96+1306-birdsLeft,95+1306-birdsLeft,92+1306-birdsLeft,92+1306-birdsLeft,93+1306-birdsLeft,90+1306-birdsLeft,89+1306-birdsLeft,89+1306-birdsLeft,88+1306-birdsLeft,88+1306-birdsLeft,85+1306-birdsLeft,81+1306-birdsLeft,81+1306-birdsLeft,81+1306-birdsLeft,79+1306-birdsLeft,80+1306-birdsLeft,81+1306-birdsLeft,82+1306-birdsLeft,84+1306-birdsLeft,81+1306-birdsLeft,78+1306-birdsLeft,75+1306-birdsLeft,70+1306-birdsLeft,64+1306-birdsLeft};
      int y1points[] = {91,88,88,87,87,87,86,82,76,70,67,66,64,62,61,63,65,67,70,74,77,80,83,85,86,86,85,85,87,90,90,91,92,94,95,96,100,103,108,111,114,116,120,122,125,129,129,126,120,114,110,105,102,97,94,93,93,93,93,93};
      int n1points=60;
      g.fillPolygon(x1points,y1points,n1points);
    }
    
    public void drawSun(Graphics g) //sun
    {
     //body
     g.setColor(Color.YELLOW);
     g.fillOval(-100+sunDown,(int)(100*Math.cos(1.0/250*sunDown)-200),100,100);
     //rays
     g.fillRect(-55+sunDown,(int)(100*Math.cos(1.0/250*sunDown)-290),10,80);
     g.fillRect(-55+sunDown,(int)(100*Math.cos(1.0/250*sunDown)-90),10,80);
     g.fillRect(-190+sunDown,(int)(100*Math.cos(1.0/250*sunDown)-155),80,10);
     g.fillRect(10+sunDown,(int)(100*Math.cos(1.0/250*sunDown)-155),80,10);
    }
    
    public void drawMoon(Graphics g) //mooon
    {
     g.setColor(Color.GRAY);
     g.fillArc(-1600+sunDown,(int)(100*Math.cos(1.0/250*sunDown)-200),100,100,90,180);
    }
    
}
