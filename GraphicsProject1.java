import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;
import javax.sound.sampled.*;
import java.awt.Toolkit;
import java.net.*;   
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.UnsupportedAudioFileException; 

public class GraphicsProject1 
{
 
 public static void main(String...args) 
 { 
  JFrame j = new JFrame();  
  MyPanel1 m = new MyPanel1();
  j.setSize(m.getSize());
  j.add(m); 
  j.setVisible(true); 
  j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
 }
 
}

class MyPanel1 extends JPanel implements ActionListener, KeyListener, MouseListener 
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
 
 MyPanel1()  
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
 
 public void music() throws Exception  
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
 
 public void music2() throws Exception  
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

 public void paintComponent(Graphics g)
 {
  if(sunDown<1550)
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
    
  if(left==0)
    words(g);
  if(left >= 10 && up == 0)
    words2(g);
  if(timer >= 25000 && timer <= 31000)
    words3(g);
  if(timer >= 31000 && up+down <=10)
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
   left+=5;
   timer+=15;
   repaint();
   if(birdsLeft >= 3000)
    birdsLeft = -1000;
   birdsLeft= birdsLeft + 10;
   if(treeLeft >= 2000)
     treeLeft = -500;
   treeLeft+= 5;
   sunDown += 2;
   if(sunDown >=3050)
     sunDown = 0;
  repaint();
 }

 public void keyPressed(KeyEvent e)
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
 
 public void playMusic() 
 {
  try
  {
   music();
  }
  catch(Exception e)
  {
  }
 }
 
 public void playMusic2() 
 {
  try
  {
   music2();
  }
  catch(Exception e)
  {
  }
 }
 
 public void keyReleased(KeyEvent e){}
 
 public void keyTyped(KeyEvent e){}
 public void mousePressed(MouseEvent e)
 {
  up=up+10;
  repaint();
 }
 
 public void mouseReleased(MouseEvent e){}

 public void mouseClicked(MouseEvent e){}

 public void mouseEntered(MouseEvent e){}
 public void mouseExited(MouseEvent e){}
 
    public void drawBackground(Graphics g,int up) //draws the background
    {
     Color blue = new Color(31,190,214);
      g.setColor(blue);
      g.fillRect(0,0,2000,1500);
      g.setColor(Color.GREEN);
      g.fillRect(0,550+up + down,2000,1000);
    }  
    
    public void drawBackground2(Graphics g)
    {
     g.setColor(Color.BLUE);
     g.fillRect(0,0,2000,1500);
     g.setColor(Color.GREEN);
     g.fillRect(0,550+up+down,2000,1000);
    }
    
    public void words(Graphics g)//displays the command to press A
    {
     g.setColor(Color.RED);
     g.setFont(new Font("TimesRoman", Font.PLAIN, 100)); 
     g.drawString("Press A",600,400);
    }
    
    public void words2(Graphics g)//displays the command to click the mouse to elevate plane
    {
      g.setColor(Color.RED);
      g.setFont(new Font("TimesRoman", Font.PLAIN, 100)); 
      g.drawString("Click the mouse",600,400);
    }
    
    public void words3(Graphics g)//displays word to land the plane
    {
      g.setColor(Color.RED);
      g.setFont(new Font("TimesRoman", Font.PLAIN, 100)); 
      g.drawString("Press down",600,400);
    }
    
    public void words4(Graphics g)
    {
      g.setColor(Color.RED);
      g.setFont(new Font("TimesRoman", Font.PLAIN, 100)); 
      g.drawString("SUCCESS!!!!!",600,400);
    }
    public void drawClouds(Graphics g)
    {
     g.setColor(Color.WHITE);
     
     g.fillOval(30,20,40,40);//each block codes for a single cloud
     g.fillOval(55,20,40,40);
     g.fillOval(80,20,40,40);
     g.fillOval(30,45,40,40);
     g.fillOval(55,45,40,40);
     g.fillOval(80,45,40,40);
     g.fillOval(90,32,40,40);
     g.fillOval(20,32,40,40);
     
     g.fillOval(30+100,20+20,40,40);
     g.fillOval(55+100,20+20,40,40);
     g.fillOval(80+100,20+20,40,40);
     g.fillOval(30+100,45+20,40,40);
     g.fillOval(55+100,45+20,40,40);
     g.fillOval(80+100,45+20,40,40);
     g.fillOval(90+100,32+20,40,40);
     g.fillOval(20+100,32+20,40,40);
      
     g.fillOval(30+350,20+50,40,40);
     g.fillOval(55+350,20+50,40,40);
     g.fillOval(80+350,20+50,40,40);
     g.fillOval(30+350,45+50,40,40);
     g.fillOval(55+350,45+50,40,40);
     g.fillOval(80+350,45+50,40,40);
     g.fillOval(90+350,32+50,40,40);
     g.fillOval(20+350,32+50,40,40);
      
      
     g.fillOval(30+700,20+10,40,40);
     g.fillOval(55+700,20+10,40,40);
     g.fillOval(80+700,20+10,40,40);
     g.fillOval(30+700,45+10,40,40);
     g.fillOval(55+700,45+10,40,40);
     g.fillOval(80+700,45+10,40,40);
     g.fillOval(90+700,32+10,40,40);
     g.fillOval(20+700,32+10,40,40);
      
     g.fillOval(30+1000,20+100,40,40);
     g.fillOval(55+1000,20+100,40,40);
     g.fillOval(80+1000,20+100,40,40);
     g.fillOval(30+1000,45+100,40,40);
     g.fillOval(55+1000,45+100,40,40);
     g.fillOval(80+1000,45+100,40,40);
     g.fillOval(90+1000,32+100,40,40);
     g.fillOval(20+1000,32+100,40,40);
      
     g.fillOval(30+1500,20+40,40,40);
     g.fillOval(55+1500,20+40,40,40);
     g.fillOval(80+1500,20+40,40,40);
     g.fillOval(30+1500,45+40,40,40);
     g.fillOval(55+1500,45+40,40,40);
     g.fillOval(80+1500,45+40,40,40);
     g.fillOval(90+1500,32+40,40,40);
     g.fillOval(20+1500,32+40,40,40);
      
     g.fillOval(30+2000,20+50,40,40);
     g.fillOval(55+2000,20+50,40,40);
     g.fillOval(80+2000,20+50,40,40);
     g.fillOval(30+2000,45+50,40,40);
     g.fillOval(55+2000,45+50,40,40);
     g.fillOval(80+2000,45+50,40,40);
     g.fillOval(90+2000,32+50,40,40);
     g.fillOval(20+2000,32+50,40,40);
    }
    
    public void drawRunway(Graphics g,int left,int up)
    {
     g.setColor(Color.GRAY);//gray runway
     g.fillRect(300-left,600+up+down,2000,1000);
     g.setColor(Color.YELLOW);//zebra strips on the runway
     for(int x = 0; x < 16; x++) 
      g.fillRect(350 + x*120 -left,650+up+down,100,50);
     g.setColor(Color.GRAY);
     g.fillRect(10000-left,600+up+down,2000,1000);
     g.setColor(Color.YELLOW);
     for(int x = 0; x < 16; x++) 
      g.fillRect(10100 + x*120 -left,650+up+down,100,50);
    }
    public void drawTree(Graphics g)
    {
     Color Brown = new Color(102,51,0);
     g.setColor(Brown);
     int npoints=48;
     int xpoints [] ={667+300-treeLeft,670+300-treeLeft,675+300-treeLeft,678+300-treeLeft,683+300-treeLeft,684+300-treeLeft,687+300-treeLeft,689+300-treeLeft,690+300-treeLeft,694+300-treeLeft,691+300-treeLeft,683+300-treeLeft,675+300-treeLeft,681+300-treeLeft,689+300-treeLeft,696+300-treeLeft,701+300-treeLeft,707+300-treeLeft,708+300-treeLeft,712+300-treeLeft,717+300-treeLeft,723+300-treeLeft,723+300-treeLeft,729+300-treeLeft,733+300-treeLeft,729+300-treeLeft,727+300-treeLeft,722+300-treeLeft,721+300-treeLeft,723+300-treeLeft,723+300-treeLeft,724+300-treeLeft,727+300-treeLeft,730+300-treeLeft,734+300-treeLeft,746+300-treeLeft,738+300-treeLeft,727+300-treeLeft,721+300-treeLeft,715+300-treeLeft,713+300-treeLeft,713+300-treeLeft,708+300-treeLeft,702+300-treeLeft,695+300-treeLeft,689+300-treeLeft,683+300-treeLeft,676+300-treeLeft };
     int ypoints [] = {236+352+up+down,229+352+up+down,227+352+up+down,221+352+up+down,215+352+up+down,208+352+up+down,197+352+up+down,187+352+up+down,175+352+up+down,162+352+up+down,149+352+up+down,136+352+up+down,131+352+up+down,122+352+up+down,126+352+up+down,126+352+up+down,125+352+up+down,133+352+up+down,137+352+up+down,133+352+up+down,127+352+up+down,125+352+up+down,122+352+up+down,120+352+up+down,125+352+up+down,127+352+up+down,135+352+up+down,145+352+up+down,158+352+up+down,169+352+up+down,175+352+up+down,189+352+up+down,201+352+up+down,209+352+up+down,221+352+up+down,233+352+up+down,235+352+up+down,232+352+up+down,230+352+up+down,229+352+up+down,233+352+up+down,239+352+up+down,235+352+up+down,229+352+up+down,226+352+up+down,231+352+up+down,232+352+up+down,234+352+up+down};
     g.fillPolygon(xpoints,ypoints,npoints);
     g.setColor(Color.GREEN);
     int x1points [] ={625+300-treeLeft,619+300-treeLeft,611+300-treeLeft,605+300-treeLeft,601+300-treeLeft,601+300-treeLeft,602+300-treeLeft,606+300-treeLeft,610+300-treeLeft,616+300-treeLeft,621+300-treeLeft,627+300-treeLeft,633+300-treeLeft,633+300-treeLeft,631+300-treeLeft,631+300-treeLeft,633+300-treeLeft,637+300-treeLeft,642+300-treeLeft,647+300-treeLeft,653+300-treeLeft,659+300-treeLeft,661+300-treeLeft,665+300-treeLeft,669+300-treeLeft,674+300-treeLeft,680+300-treeLeft,687+300-treeLeft,693+300-treeLeft,699+300-treeLeft,705+300-treeLeft,711+300-treeLeft,716+300-treeLeft,719+300-treeLeft,722+300-treeLeft,723+300-treeLeft,724+300-treeLeft,728+300-treeLeft,732+300-treeLeft,737+
      300-treeLeft,742+300-treeLeft,748+300-treeLeft,754+300-treeLeft,760+300-treeLeft,765+300-treeLeft,770+300-treeLeft,775+300-treeLeft,779+300-treeLeft,782+300-treeLeft,785+300-treeLeft,787+300-treeLeft,788+300-treeLeft,788+300-treeLeft,786+300-treeLeft,790+300-treeLeft,796+300-treeLeft,802+300-treeLeft,806+300-treeLeft,808+300-treeLeft,808+300-treeLeft,809+300-treeLeft,807+300-treeLeft,804+300-treeLeft,801+300-treeLeft,796+300-treeLeft,792+300-treeLeft,788+300-treeLeft,787+300-treeLeft,787+300-treeLeft,782+300-treeLeft,779+300-treeLeft,779+300-treeLeft,779+300-treeLeft,774+300-treeLeft,768+300-treeLeft,761+300-treeLeft,755+300-treeLeft,749+300-treeLeft,744+300-treeLeft,739+300-treeLeft,734+300-treeLeft,728+300-treeLeft,723+300-treeLeft,719+300-treeLeft,716+300-treeLeft,713+300-treeLeft,710+300-treeLeft,705+300-treeLeft,702+300-treeLeft,697+300-treeLeft,690+300-treeLeft,684+300-treeLeft,680+300-treeLeft,677+300-treeLeft,672+300-treeLeft,667+300-treeLeft,662+300-treeLeft,658+300-treeLeft,654+300-treeLeft,649+300-treeLeft,642+300-treeLeft,636+300-treeLeft,631+300-treeLeft,627+300-treeLeft,625+300-treeLeft,625+300-treeLeft};
     int y1points [] ={113+352+up+down,113+352+up+down,111+352+up+down,106+352+up+down,98+352+up+down,87+352+up+down,79+352+up+down,73+352+up+down,67+352+up+down,65+352+up+down,64+352+up+down,63+352+up+down,64+352+up+down,57+352+up+down,51+352+up+down,43+352+up+down,36+352+up+down,31+352+up+down,26+352+up+down,23+352+up+down,23+352+up+down,24+352+up+down,17+352+up+down,11+352+up+down,7+352+up+down,3+352+up+down,2+352+up+down,1+352+up+down,1+352+up+down,1+352+up+down,3+352+up+down,6+352+up+down,9+352+up+down,14+352+up+down,20+352+up+down,24+352+up+down,28+352+up+down,25+352+up+down,20+352+up+down,17+352+up+down,14+352+up+down,14+352+up+down,14+352+up+down,15+352+up+down,17+352+up+down,20+352+up+down,23+352+up+down,28+352+up+down,32+352+up+down,37+352+up+down,43+352+up+down,48+352+up+down,52+352+up+down,56+352+up+down,60+352+up+down,63+352+up+down,66+352+up+down,71+352+up+down,76+352+up+down,83+352+up+down,91+352+up+down,98+352+up+down,103+352+up+down,108+352+up+down,112+352+up+down,113+352+up+down,113+352+up+down,116+352+up+down,121+352+up+down,122+352+up+down,121+352+up+down,125+352+up+down,130+352+up+down,133+352+up+down,136+352+up+down,136+352+up+down,136+352+up+down,134+352+up+down,131+352+up+down,127+352+up+down,123+352+up+down,120+352+up+down,115+352+up+down,110+352+up+down,106+352+up+down,109+352+up+down,114+352+up+down,119+352+up+down,124+352+up+down,128+352+up+down,127+352+up+down,125+352+up+down,122+352+up+down,126+352+up+down,130+352+up+down,134+352+up+down,134+352+up+down,131+352+up+down,128+352+up+down,130+352+up+down,132+352+up+down,131+352+up+down,129+352+up+down,125+352+up+down,122+352+up+down,117+352+up+down};
     int n1points = 106;
     g.fillPolygon(x1points,y1points,n1points);
    }
    
    public void drawAirport(Graphics g)
    {
     g.setColor(Color.BLACK);//roof of airport
     g.fillRect(0-left,250+up,200,75);
     g.fillArc(20-left,275+up,300,100,0,90);
     g.setColor(new Color(135,206,235));//window of airport
     g.fillRect(0-left,325+up,320,100);
     g.setColor(Color.GRAY);//body of airport
     g.fillRect(0-left,425+up,320,150);
     g.setColor(Color.BLACK);//body of airport
     g.drawLine(40-left,325+up,40-left,425+up);
     g.drawLine(80-left,325+up,80-left,425+up);
     g.drawLine(120-left,325+up,120-left,425+up);
     g.drawLine(160-left,325+up,160-left,425+up);
     g.drawLine(200-left,325+up,200-left,425+up);
     g.drawLine(240-left,325+up,240-left,425+up);
     g.drawLine(280-left,325+up,280-left,425+up);
     g.drawLine(320-left,325+up,320-left,425+up);
    } 
    public void drawTower(Graphics g)
    {
     /*Color DGRAY = new Color(96,96,96);
     g.setColor(DGRAY);
     int xpoints[]={1470-left,1482-left,1479-left,1456-left,1458-left,1426-left,1426-left,1449-left,1449-left,1456-left,1459-left,1438-left,1436-left,1444-left,1447-left,1459-left,1458-left,1448-left,1448-left,1438-left,1440-left,1572-left,1573-left,1559-left,1562-left,1552-left,1551-left,1563-left,1561-left,1572-left,1572-left,1555-left,1551-left,1562-left,1561-left,1587-left,1584-left,1587-left,1553-left,1553-left,1530-left,1529-left,1539-left};
     int ypoints[]={154,173,190,188,199,200,219,278,291,292,342,343,364,366,385,385,544,544,588,591,605,606,590,588,543,541,385,385,366,366,340,340,300,291,278,218,207,198,197,187,187,174,155};
     int npoints=43;
     g.fillPolygon(xpoints,ypoints,npoints);
     g.setColor(Color.YELLOW);
     int x1points[]={1427-left,1425-left,1451-left,1561-left,1585-left,1585-left};
     int y1points[]={205,218,275,275,218,205};
     int n1points=6;
     g.fillPolygon(x1points,y1points,n1points);*/
     
     Color DGRAY = new Color(96,96,96);
     g.setColor(DGRAY);
     int xpoints[] = {276+900-left,333+900-left,329+900-left,329+900-left,353+900-left,353+900-left,259+900-left,259+900-left,283+900-left,283+900-left};
     int ypoints[] = {62+104,62+104,73+104,88+104,88+104,98+104,98+104,88+104,88+104,73+104};
     int npoints = 10;
     g.fillPolygon(xpoints,ypoints,npoints);
     Color LBLUE =  new Color(51,255,255);
     g.setColor(LBLUE);
     int x1points[] = {220+900-left,389+900-left,389+900-left,384+900-left,384+900-left,361+900-left,250+900-left,226+900-left,226+900-left,220+900-left};
     int y1points[] = {100+104,100+104,107+104,107+104,120+104,178+104,178+104,120+104,107+104,107+104};
     int n1points = 10;
     g.fillPolygon(x1points,y1points,n1points);
     g.setColor(DGRAY);
     int x2points[] = {250+900-left,251+900-left,259+900-left,260+900-left,241+900-left,239+900-left,250+900-left,249+900-left,259+900-left,259+900-left,250+900-left,249+900-left,240+900-left,238+900-left,371+900-left,372+900-left,361+900-left,362+900-left,352+900-left,351+900-left,362+900-left,361+900-left,372+900-left,372+900-left,353+900-left,352+900-left,363+900-left,360+900-left};
     int y2points[] = {178+104,197+104,199+104,242+104,244+104,265+104,267+104,285+104,286+104,443+104,444+104,488+104,490+104,509+104,508+104,491+104,489+104,445+104,445+104,287+104,286+104,268+104,267+104,243+104,244+104,199+104,198+104,179+104};
     int n2points = 28;
     g.fillPolygon(x2points,y2points,n2points);
     
    }
     
    public void drawAirplane(Graphics g)
    {
     if(left < 300)
       stayLeft = left;
     if(up < 400)
       stayDown = up;
     //body
     g.setColor(Color.WHITE);
     g.fillRect(350+stayLeft,550-stayDown-down,450,90);
     //top head
     Polygon c = new Polygon();
     c.addPoint(800+stayLeft,550-stayDown-down);
     c.addPoint(850+stayLeft,575-stayDown-down);
     c.addPoint(800+stayLeft,575-stayDown-down);
     g.fillPolygon(c);  
     //bottom head                                                                                                                                                                                                
     g.fillArc(725+stayLeft,510-stayDown-down,120,130,270,90);
     //bottom tail
     g.fillArc(270+stayLeft,460-stayDown-down,200,180,180,90);
     //top tail
     Polygon p = new Polygon();
     p.addPoint(350+stayLeft,550-stayDown-down);
     p.addPoint(300+stayLeft,500-stayDown-down);
     p.addPoint(280+stayLeft,400-stayDown-down);
     p.addPoint(260+stayLeft,400-stayDown-down);
     p.addPoint(270+stayLeft,550-stayDown-down);
     g.fillPolygon(p);
     //wing
     Polygon a = new Polygon();
     a.addPoint(700+stayLeft,580-stayDown-down);
     a.addPoint(700+stayLeft,600-stayDown-down);
     a.addPoint(650+stayLeft,620-stayDown-down);
     a.addPoint(400+stayLeft,590-stayDown-down);
     a.addPoint(400+stayLeft,580-stayDown-down);
     g.setColor(Color.BLACK);
     g.drawPolygon(a);
     //wheels
     g.fillOval(500+stayLeft,650-stayDown-down,30,30);
     g.fillOval(750+stayLeft,650-stayDown-down,30,30);
     g.fillRect(513+stayLeft,640-stayDown-down,5,15);
     g.fillRect(763+stayLeft,640-stayDown-down,5,15);
     //engine
     Color y = new Color(200,200,200);
     g.setColor(y);
     g.fillRect(700+stayLeft,600-stayDown-down,75,50);
     Color z = new Color(180,180,180);
     g.setColor(z);
     Polygon b = new Polygon();
     b.addPoint(700+stayLeft,600-stayDown-down);
     b.addPoint(650+stayLeft,620-stayDown-down);
     b.addPoint(650+stayLeft,630-stayDown-down);
     b.addPoint(700+stayLeft,650-stayDown-down);
     g.fillPolygon(b);
     //captain window
     g.setColor(Color.BLACK);
     Polygon d = new Polygon();
     d.addPoint(820+stayLeft,560-stayDown-down);
     d.addPoint(795+stayLeft,560-stayDown-down);
     d.addPoint(793+stayLeft,565-stayDown-down);
     d.addPoint(793+stayLeft,570-stayDown-down);
     d.addPoint(840+stayLeft,570-stayDown-down);
     g.fillPolygon(d);
     //windows
     for(int x = 0; x < 20; x++)
      g.fillRect(350+x*20+stayLeft,565-stayDown-down,10,10);
     //door
     g.drawRect(755+stayLeft,555-stayDown-down,20,40);
     
    }
    
    public void drawBirds(Graphics g)
    {
      g.setColor(Color.BLACK);
      int xpoints[] = {60+2000-birdsLeft,64+2000-birdsLeft,68+2000-birdsLeft,72+2000-birdsLeft,75+2000-birdsLeft,80+2000-birdsLeft,85+2000-birdsLeft,90+2000-birdsLeft,92+2000-birdsLeft,94+2000-birdsLeft,97+2000-birdsLeft,101+2000-birdsLeft,106+2000-birdsLeft,108+2000-birdsLeft,113+2000-birdsLeft,116+2000-birdsLeft,117+2000-birdsLeft,113+2000-birdsLeft,110+2000-birdsLeft,107+2000-birdsLeft,105+2000-birdsLeft,103+2000-birdsLeft,101+2000-birdsLeft,103+2000-birdsLeft,107+2000-birdsLeft,110+2000-birdsLeft,114+2000-birdsLeft,117+2000-birdsLeft,120+2000-birdsLeft,118+2000-birdsLeft,114+2000-birdsLeft,110+2000-birdsLeft,106+2000-birdsLeft,102+2000-birdsLeft,99+2000-birdsLeft,98+2000-birdsLeft,96+2000-birdsLeft,95+2000-birdsLeft,92+2000-birdsLeft,92+2000-birdsLeft,93+2000-birdsLeft,90+2000-birdsLeft,89+2000-birdsLeft,89+2000-birdsLeft,88+2000-birdsLeft,88+2000-birdsLeft,85+2000-birdsLeft,81+2000-birdsLeft,81+2000-birdsLeft,81+2000-birdsLeft,79+2000-birdsLeft,80+2000-birdsLeft,81+2000-birdsLeft,82+2000-birdsLeft,84+2000-birdsLeft,81+2000-birdsLeft,78+2000-birdsLeft,75+2000-birdsLeft,70+2000-birdsLeft,64+2000-birdsLeft};
      int ypoints[] = {91,88,88,87,87,87,86,82,76,70,67,66,64,62,61,63,65,67,70,74,77,80,83,85,86,86,85,85,87,90,90,91,92,94,95,96,100,103,108,111,114,116,120,122,125,129,129,126,120,114,110,105,102,97,94,93,93,93,93,93};
      int npoints=60;
      g.fillPolygon(xpoints,ypoints,npoints);
      int x1points[] = {60+2100-birdsLeft,64+2100-birdsLeft,68+2100-birdsLeft,72+2100-birdsLeft,75+2100-birdsLeft,80+2100-birdsLeft,85+2100-birdsLeft,90+2100-birdsLeft,92+2100-birdsLeft,94+2100-birdsLeft,97+2100-birdsLeft,101+2100-birdsLeft,106+2100-birdsLeft,108+2100-birdsLeft,113+2100-birdsLeft,116+2100-birdsLeft,117+2100-birdsLeft,113+2100-birdsLeft,110+2100-birdsLeft,107+2100-birdsLeft,105+2100-birdsLeft,103+2100-birdsLeft,101+2100-birdsLeft,103+2100-birdsLeft,107+2100-birdsLeft,110+2100-birdsLeft,114+2100-birdsLeft,117+2100-birdsLeft,120+2100-birdsLeft,118+2100-birdsLeft,114+2100-birdsLeft,110+2100-birdsLeft,106+2100-birdsLeft,102+2100-birdsLeft,99+2100-birdsLeft,98+2100-birdsLeft,96+2100-birdsLeft,95+2100-birdsLeft,92+2100-birdsLeft,92+2100-birdsLeft,93+2100-birdsLeft,90+2100-birdsLeft,89+2100-birdsLeft,89+2100-birdsLeft,88+2100-birdsLeft,88+2100-birdsLeft,85+2100-birdsLeft,81+2100-birdsLeft,81+2100-birdsLeft,81+2100-birdsLeft,79+2100-birdsLeft,80+2100-birdsLeft,81+2100-birdsLeft,82+2100-birdsLeft,84+2100-birdsLeft,81+2100-birdsLeft,78+2100-birdsLeft,75+2100-birdsLeft,70+2100-birdsLeft,64+2100-birdsLeft};
      int y1points[] = {91,88,88,87,87,87,86,82,76,70,67,66,64,62,61,63,65,67,70,74,77,80,83,85,86,86,85,85,87,90,90,91,92,94,95,96,100,103,108,111,114,116,120,122,125,129,129,126,120,114,110,105,102,97,94,93,93,93,93,93};
      int n1points=60;
      g.fillPolygon(x1points,y1points,n1points);
    }
    
    public void drawSun(Graphics g)
    {
     g.setColor(Color.YELLOW);
     g.fillOval(-100+sunDown,(int)(100*Math.cos(1.0/250*sunDown)+100),100,100);
     g.fillRect(-55+sunDown,(int)(100*Math.cos(1.0/250*sunDown)+10),10,80);
     g.fillRect(-55+sunDown,(int)(100*Math.cos(1.0/250*sunDown)+210),10,80);
     g.fillRect(-190+sunDown,(int)(100*Math.cos(1.0/250*sunDown)+145),80,10);
     g.fillRect(10+sunDown,(int)(100*Math.cos(1.0/250*sunDown)+145),80,10);
    }
    
    public void drawMoon(Graphics g)
    {
     g.setColor(Color.GRAY);
     g.fillArc(-1600+sunDown,(int)(100*Math.cos(1.0/250*sunDown)+100),100,100,90,180);
    }
    
}
