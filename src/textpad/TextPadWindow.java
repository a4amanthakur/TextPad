package textpad;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.BadLocationException;
import javax.swing.undo.UndoManager;
import com.sun.speech.freetts.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JColorChooser;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;
import jdk.nashorn.internal.runtime.FindProperty;
/**
 *
 * @author thakur-huni
 */
public class TextPadWindow extends javax.swing.JFrame implements Runnable{

    int colCount = 1, LineCount = 1, docCount = 1,start,end;
    boolean savedOnce=false,fileSaved = false, sts;
    int findNextCounter=0,flag = 1,curContentLen=0,prevContentLen=0,defaultFontStyle=0,defaultFontSize=20;
    String title, openFilePath, fileName="Untitled Document",txt,defaultFontFamily="Arial",voiceData,find,replace;
    JFileChooser selectedFile;
    File file;
    BufferedReader read;
    BufferedWriter b_write;
    FileReader f_read;
    FileWriter f_write;
    StringBuilder str;
    Image icon;
    UndoManager undoManager;
    private final String VOICENAME="kevin16";
    Voice voice;
    VoiceManager voiceManager;
    Highlighter highlight;
    Pattern pattern;
    Matcher matcher;
    String fontStyles[]={"Normal","Bold","Italic","Italic & Bold"};
    String fontFamily[]={"Arial","Book Antiqua","Calibri","Cambria","Didot","Garamond","Georgia","Helvetica","Serif","Sans-serif","Ubuntu"};
    Locale locale ;    
    Thread thread;
    Highlighter highlighter;
    HighlightPainter painter;
    private int matchCount;
    LinkedList<FindNextData> findNextData;
    FindNextData findData;
    
    public TextPadWindow(int docCount) {
    	loadLocale();
        setVisible(true);
        initComponents();
        findNextData=new LinkedList<FindNextData>();
        selectedFile = new JFileChooser();
        str = new StringBuilder("");
        this.docCount = docCount;
        loadTitleContent();
        undoRedoMenu();
        
        txtContent.setFont(new Font("Arial",0,20));
        
        setHighlighter();
        //if close clicked
        frameCloseOperation();
    }
    private void setHighlighter()
    {
        highlighter = txtContent.getHighlighter();
        painter = new DefaultHighlighter.DefaultHighlightPainter(Color.yellow);
        
    }
    
    private void frameCloseOperation()
    {
       this.addWindowListener(new WindowAdapter() {
       public void windowClosing(WindowEvent e) 
       {
           if(fileSaved==false)
          {
             exitOperation();
          }
       }
     });
    }
    private void loadLocale()
    {
    }
    private void setFontSize(int size)
    {
        txtContent.setFont(new Font(defaultFontFamily,defaultFontStyle,size));
    }
    private void setFontStyle(int size)
    {
        txtContent.setFont(new Font(defaultFontFamily,size,defaultFontSize));
    }
    private void setFontFamily(String fontFamily)
    {
        txtContent.setFont(new Font(fontFamily,defaultFontStyle,defaultFontSize));
    }
    private  void setSpeechVariables()
    {
        voiceManager=VoiceManager.getInstance();
        voice=voiceManager.getVoice(VOICENAME);
        voice.allocate();
    }
    private void speech(String txt)
    {
        setSpeechVariables();
        voice.speak(txt);
    }
    private void loadTitleContent() {
        txtContent.grabFocus();
        title = "Untitled Document " + docCount + " - TextPad";
        this.setTitle(title);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Icons/logo.png")));
       
      
    }
    private void loadTitleContent(String name) {
        str.delete(0, str.length());
        //fileName = name;
        str.append(name);
        str.append(" - TextPad");
        title = str.toString();
        setTitle(title);
    }
    private void openFileFromFileSystem() {

        int a = selectedFile.showSaveDialog(this);
        if (a == JFileChooser.APPROVE_OPTION) {
            try {
                //find absolute path
                openFilePath = selectedFile.getSelectedFile().getAbsolutePath();

                //give path
                file = new File(openFilePath);
                if (file.exists()) {
                    //read file
                    f_read = new FileReader(file);
                    read = new BufferedReader(f_read);
                    str.delete(0, str.length());
                    int ch;
                    while ((ch = read.read()) != -1) {
                        str.append((char) ch);
                    }
                    String s=str.toString();
                    //write to pad
                    txtContent.setText(s);
                    
                    //get file name
                    
                    fileName = file.getName();
                    loadTitleContent(fileName);
                    fileSaved = true;
                    savedOnce=true;
                } else {
                    JOptionPane.showMessageDialog(this, "File not exists.");
                }

                f_read.close();
                read.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(TextPadWindow.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(TextPadWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No file selected.");
        }
    }
    private boolean saveFile()  {
        try {
            flag=1;
            //if file not saved(not single time)
            if (fileSaved == false && savedOnce==false) 
            {
                fileSaved = true;
                savedOnce=true;
                int a = selectedFile.showSaveDialog(this);
                if (a == JFileChooser.APPROVE_OPTION) {
                    //find absolute path
                    openFilePath = selectedFile.getSelectedFile().getAbsolutePath();
                    //give path
                    file=new File(openFilePath);
                    if(!file.exists())
                    {
                            file.createNewFile();
                            
                    }
                    
                   flag=1;

                } 
                else 
                {
                    flag=0;
                    fileSaved = false;
                    JOptionPane.showMessageDialog(this, "No file selected.");
                   
                }
            }//end of if
            
                if(flag==1)
                {
                     fileName=file.getName();
                    f_write=new FileWriter(file);
                    b_write=new BufferedWriter(f_write);
                    b_write.write(txtContent.getText());
                    b_write.close();
                    f_write.close();
                        //get file name
                    loadTitleContent(fileName);
                    sts = true;
                    fileSaved = true;
                }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TextPadWindow.class.getName()).log(Level.SEVERE, null, ex);

            
        } catch (IOException ex) {
            System.out.println("io exp:");
        }
        return sts;
    }
    private boolean saveAsFile() {
        sts = false;
        fileSaved = true;
        int a = selectedFile.showSaveDialog(this);
        if (a == JFileChooser.APPROVE_OPTION) {
            try {
                //find absolute path
                openFilePath = selectedFile.getSelectedFile().getAbsolutePath();

                //give path
                file = new File(openFilePath);
                if (!file.exists()) {
                    file.createNewFile();
                }

                //write file
                fileName=file.getName();
                    f_write=new FileWriter(file);
                    b_write=new BufferedWriter(f_write);
                    b_write.write(txtContent.getText());
                    b_write.close();
                    f_write.close();
                        //get file name
                    loadTitleContent(fileName);
                    sts = true;
                    fileSaved = true;

            } catch (FileNotFoundException ex) {
                Logger.getLogger(TextPadWindow.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(TextPadWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            fileSaved = false;
            sts=false;
            JOptionPane.showMessageDialog(this, "No file selected.");
        }
        return sts;
    }
    private void exitOperation()
    {
        if(thread!=null)
               {
                   
                   thread.stop();
                   thread=null;
                   voice.deallocate();
               }
        int i=JOptionPane.showConfirmDialog(this, "Do you want to save document first(Yes / No).?","Select an Option",1);
          if(i==0)//click yes
            {
              
                if(saveAsFile()==true)
                {
                 
                    dispose();
                }
                else
                {
                    exitOperation();
                }
                
            }
          else if(i==1)//click no
          {
              dispose();
          }
    }
    private void undoRedoMenu()
    {
        undoManager=new UndoManager();
        txtContent.getDocument().addUndoableEditListener(
                 new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                undoManager.addEdit(e.getEdit());
            }
        }
        );
    }
    
    private void undo()
    {
        undoManager.undo();
    }
    private void redo()
    {
        undoManager.redo();
    }
    
    private void searchText(String data) 
    {
        highlighter.removeAllHighlights();
        findNextData.clear();
       // txtContent.getHighlighter();
        
        pattern=Pattern.compile("\\b"+data+"\\b");
        matcher=pattern.matcher(txtContent.getText());
        matchCount=0;   
        while(matcher.find())
        {
            
            start=matcher.start();
            end=matcher.end();
            //store find data
            findData=new FindNextData(start,end);
            findNextData.add(findData);
            
            matchCount++;
            try
            {
                highlighter.addHighlight(start , end, painter);
            
            }
            catch(BadLocationException e)
            {
                
            }
        
                    
        }
        lblFind.setText(data+" : "+matchCount+" matches.");
        
    }
    
    private void replaceText(String data) 
    {
        lblFind.setText(null);
        pattern=Pattern.compile(find);
        matcher=pattern.matcher(txtContent.getText());
        txtContent.setText(matcher.replaceAll(data));
       // System.out.println("after:"+matcher.replaceAll(data));
    }
    private void findNextFun(int count)
    {
        findData=findNextData.get(count);
        
        txtContent.select(findData.start, findData.end);
        txtContent.setSelectionColor(Color.red);
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem2 = new javax.swing.JMenuItem();
        pnlParent = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtContent = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        pnlStsBar = new javax.swing.JPanel();
        lblLineColCount = new javax.swing.JLabel();
        lblFind = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        saveMenu = new javax.swing.JMenu();
        newMenu = new javax.swing.JMenuItem();
        openMenu = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
        saveAsMenu = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        printMenu = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        exitMenu = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        undoMenu = new javax.swing.JMenuItem();
        redoMenu = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        cutMenu = new javax.swing.JMenuItem();
        copyMenu = new javax.swing.JMenuItem();
        pasteMenu = new javax.swing.JMenuItem();
        deleteMenu = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        findMenu = new javax.swing.JMenuItem();
        findNextMenu = new javax.swing.JMenuItem();
        replaceMenu = new javax.swing.JMenuItem();
        goToMenu = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        selectAllMenu = new javax.swing.JMenuItem();
        speechMenu = new javax.swing.JMenu();
        speechSelectedMenu = new javax.swing.JMenuItem();
        speechAllMenu = new javax.swing.JMenuItem();
        playSpeakMenu = new javax.swing.JMenuItem();
        pauseSpeakMenu = new javax.swing.JMenuItem();
        stopSpeakMenu = new javax.swing.JMenuItem();
        fontMenu = new javax.swing.JMenu();
        wordWrapMenu = new javax.swing.JCheckBoxMenuItem();
        jMenu1 = new javax.swing.JMenu();
        fontStyleMenu = new javax.swing.JMenu();
        customFontStyle = new javax.swing.JMenuItem();
        fontSizeMenu = new javax.swing.JMenu();
        customSize = new javax.swing.JMenuItem();
        fontColorMenu = new javax.swing.JMenu();
        customColor = new javax.swing.JMenuItem();
        fontFamilyMenu = new javax.swing.JMenu();
        customFontFamily = new javax.swing.JMenuItem();
        viewMenu = new javax.swing.JMenu();
        stsBarMenu = new javax.swing.JCheckBoxMenuItem();
        jMenu2 = new javax.swing.JMenu();
        blackBGMenu = new javax.swing.JMenuItem();
        whiteBGMenu = new javax.swing.JMenuItem();
        customBg = new javax.swing.JMenuItem();
        customFG = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        pnlParent.setBackground(new java.awt.Color(247, 247, 235));

        txtContent.setColumns(20);
        txtContent.setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        txtContent.setRows(5);
        txtContent.setBorder(null);
        txtContent.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtContentCaretUpdate(evt);
            }
        });
        txtContent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContentKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(txtContent);

        javax.swing.GroupLayout pnlParentLayout = new javax.swing.GroupLayout(pnlParent);
        pnlParent.setLayout(pnlParentLayout);
        pnlParentLayout.setHorizontalGroup(
            pnlParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlParentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlParentLayout.setVerticalGroup(
            pnlParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
        );

        lblLineColCount.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblLineColCount.setText("Ln 1, Col 1 ");

        javax.swing.GroupLayout pnlStsBarLayout = new javax.swing.GroupLayout(pnlStsBar);
        pnlStsBar.setLayout(pnlStsBarLayout);
        pnlStsBarLayout.setHorizontalGroup(
            pnlStsBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStsBarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblLineColCount)
                .addContainerGap())
        );
        pnlStsBarLayout.setVerticalGroup(
            pnlStsBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStsBarLayout.createSequentialGroup()
                .addComponent(lblLineColCount)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        lblFind.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFind)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlStsBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlStsBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFind))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jMenuBar1.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        jMenuBar1.setMargin(new java.awt.Insets(0, 0, 4, 0));

        saveMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/file.png"))); // NOI18N
        saveMenu.setText("File");
        saveMenu.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        saveMenu.setMargin(new java.awt.Insets(2, 2, 0, 0));

        newMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        newMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/newFile.png"))); // NOI18N
        newMenu.setText("New");
        newMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newMenuActionPerformed(evt);
            }
        });
        saveMenu.add(newMenu);

        openMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        openMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/openFile.png"))); // NOI18N
        openMenu.setText("Open");
        openMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuActionPerformed(evt);
            }
        });
        saveMenu.add(openMenu);
        saveMenu.add(jSeparator2);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/saveFile.png"))); // NOI18N
        jMenuItem1.setText("Save");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        saveMenu.add(jMenuItem1);

        saveAsMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/saveAsFile.png"))); // NOI18N
        saveAsMenu.setText("Save As...");
        saveAsMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsMenuActionPerformed(evt);
            }
        });
        saveMenu.add(saveAsMenu);
        saveMenu.add(jSeparator3);

        printMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        printMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/printFile.png"))); // NOI18N
        printMenu.setText("Print");
        saveMenu.add(printMenu);
        saveMenu.add(jSeparator1);

        exitMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        exitMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/exit.png"))); // NOI18N
        exitMenu.setText("Exit");
        exitMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuActionPerformed(evt);
            }
        });
        saveMenu.add(exitMenu);

        jMenuBar1.add(saveMenu);

        editMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/editFile.png"))); // NOI18N
        editMenu.setText("Edit");
        editMenu.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        editMenu.setMargin(new java.awt.Insets(2, 2, 0, 0));

        undoMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        undoMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/undo.png"))); // NOI18N
        undoMenu.setText("Undo");
        undoMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoMenuActionPerformed(evt);
            }
        });
        editMenu.add(undoMenu);

        redoMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        redoMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/redo.png"))); // NOI18N
        redoMenu.setText("Redo");
        redoMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redoMenuActionPerformed(evt);
            }
        });
        editMenu.add(redoMenu);
        editMenu.add(jSeparator4);

        cutMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        cutMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/cut.png"))); // NOI18N
        cutMenu.setText("Cut");
        cutMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cutMenuActionPerformed(evt);
            }
        });
        editMenu.add(cutMenu);

        copyMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        copyMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/copy.png"))); // NOI18N
        copyMenu.setText("Copy");
        copyMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyMenuActionPerformed(evt);
            }
        });
        editMenu.add(copyMenu);

        pasteMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        pasteMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/paste.png"))); // NOI18N
        pasteMenu.setText("Paste");
        editMenu.add(pasteMenu);

        deleteMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        deleteMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/delete.png"))); // NOI18N
        deleteMenu.setText("Delete");
        deleteMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteMenuActionPerformed(evt);
            }
        });
        editMenu.add(deleteMenu);
        editMenu.add(jSeparator5);

        findMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        findMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/search.png"))); // NOI18N
        findMenu.setText("Find...");
        findMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findMenuActionPerformed(evt);
            }
        });
        editMenu.add(findMenu);

        findNextMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        findNextMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/findNex.png"))); // NOI18N
        findNextMenu.setText("Find Next");
        findNextMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findNextMenuActionPerformed(evt);
            }
        });
        editMenu.add(findNextMenu);

        replaceMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        replaceMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/replace.png"))); // NOI18N
        replaceMenu.setText("Replace");
        replaceMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                replaceMenuActionPerformed(evt);
            }
        });
        editMenu.add(replaceMenu);

        goToMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        goToMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/goTo.png"))); // NOI18N
        goToMenu.setText("Go To...");
        goToMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goToMenuActionPerformed(evt);
            }
        });
        editMenu.add(goToMenu);
        editMenu.add(jSeparator6);

        selectAllMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        selectAllMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/selectAll.png"))); // NOI18N
        selectAllMenu.setText("Select All");
        selectAllMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAllMenuActionPerformed(evt);
            }
        });
        editMenu.add(selectAllMenu);

        jMenuBar1.add(editMenu);

        speechMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/speak.png"))); // NOI18N
        speechMenu.setText("Speech");
        speechMenu.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        speechMenu.setMargin(new java.awt.Insets(2, 2, 0, 0));

        speechSelectedMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        speechSelectedMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/select.png"))); // NOI18N
        speechSelectedMenu.setText("Speak Selected");
        speechSelectedMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                speechSelectedMenuActionPerformed(evt);
            }
        });
        speechMenu.add(speechSelectedMenu);

        speechAllMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        speechAllMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/selectAll.png"))); // NOI18N
        speechAllMenu.setText("Speak All");
        speechAllMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                speechAllMenuActionPerformed(evt);
            }
        });
        speechMenu.add(speechAllMenu);

        playSpeakMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/play.png"))); // NOI18N
        playSpeakMenu.setText("Play");
        playSpeakMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playSpeakMenuActionPerformed(evt);
            }
        });
        speechMenu.add(playSpeakMenu);

        pauseSpeakMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/pause.png"))); // NOI18N
        pauseSpeakMenu.setText("Pause");
        pauseSpeakMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseSpeakMenuActionPerformed(evt);
            }
        });
        speechMenu.add(pauseSpeakMenu);

        stopSpeakMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/stop.png"))); // NOI18N
        stopSpeakMenu.setText("Stop");
        stopSpeakMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopSpeakMenuActionPerformed(evt);
            }
        });
        speechMenu.add(stopSpeakMenu);

        jMenuBar1.add(speechMenu);

        fontMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/format.png"))); // NOI18N
        fontMenu.setText("Format");
        fontMenu.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        fontMenu.setMargin(new java.awt.Insets(2, 2, 0, 0));

        wordWrapMenu.setText("Word Wrap");
        wordWrapMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/wordWrap.png"))); // NOI18N
        wordWrapMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wordWrapMenuActionPerformed(evt);
            }
        });
        fontMenu.add(wordWrapMenu);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/font.png"))); // NOI18N
        jMenu1.setText("Font...");

        fontStyleMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/t.png"))); // NOI18N
        fontStyleMenu.setText("Font Style");

        customFontStyle.setText("Choose Custom...");
        customFontStyle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customFontStyleActionPerformed(evt);
            }
        });
        fontStyleMenu.add(customFontStyle);

        jMenu1.add(fontStyleMenu);

        fontSizeMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/fontResize.png"))); // NOI18N
        fontSizeMenu.setText("Font Size");
        fontSizeMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fontSizeMenuActionPerformed(evt);
            }
        });

        customSize.setText("Choose Custom...");
        customSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customSizeActionPerformed(evt);
            }
        });
        fontSizeMenu.add(customSize);

        jMenu1.add(fontSizeMenu);

        fontColorMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/fontColor.png"))); // NOI18N
        fontColorMenu.setText("Font Color");

        customColor.setText("Choose Custom...");
        customColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customColorActionPerformed(evt);
            }
        });
        fontColorMenu.add(customColor);

        jMenu1.add(fontColorMenu);

        fontFamilyMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/fontStyle.png"))); // NOI18N
        fontFamilyMenu.setText("Font Family");

        customFontFamily.setText("Choose Custom...");
        customFontFamily.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customFontFamilyActionPerformed(evt);
            }
        });
        fontFamilyMenu.add(customFontFamily);

        jMenu1.add(fontFamilyMenu);

        fontMenu.add(jMenu1);

        jMenuBar1.add(fontMenu);

        viewMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/view.png"))); // NOI18N
        viewMenu.setText("View");
        viewMenu.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        viewMenu.setMargin(new java.awt.Insets(2, 2, 0, 0));

        stsBarMenu.setSelected(true);
        stsBarMenu.setText("Status Bar");
        stsBarMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stsBarMenuActionPerformed(evt);
            }
        });
        viewMenu.add(stsBarMenu);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/background.png"))); // NOI18N
        jMenu2.setText("Background Settings");

        blackBGMenu.setText("Black Background");
        blackBGMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blackBGMenuActionPerformed(evt);
            }
        });
        jMenu2.add(blackBGMenu);

        whiteBGMenu.setText("White Background");
        whiteBGMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                whiteBGMenuActionPerformed(evt);
            }
        });
        jMenu2.add(whiteBGMenu);

        customBg.setText("Custom Background");
        customBg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customBgActionPerformed(evt);
            }
        });
        jMenu2.add(customBg);

        customFG.setText("Custom Forground");
        customFG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customFGActionPerformed(evt);
            }
        });
        jMenu2.add(customFG);

        viewMenu.add(jMenu2);

        jMenuBar1.add(viewMenu);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/help.png"))); // NOI18N
        jMenu3.setText("Help");
        jMenu3.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        jMenu3.setMargin(new java.awt.Insets(2, 2, 0, 0));

        jMenuItem3.setText("View Help");
        jMenu3.add(jMenuItem3);

        jMenuItem4.setText("About TextPad");
        jMenu3.add(jMenuItem4);

        jMenuItem5.setText("About Developers");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlParent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(pnlParent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void newMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newMenuActionPerformed
        docCount++;
        TextPadWindow win = new TextPadWindow(docCount);
    }//GEN-LAST:event_newMenuActionPerformed

    private void openMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuActionPerformed
        openFileFromFileSystem();
    }//GEN-LAST:event_openMenuActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

            if (saveFile() == true) {
                JOptionPane.showMessageDialog(this, "File Saved Successfully...");
            }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void saveAsMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsMenuActionPerformed
        if (saveAsFile() == true) {
            JOptionPane.showMessageDialog(this, "File Saved Successfully");
        }
    }//GEN-LAST:event_saveAsMenuActionPerformed

    private void exitMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuActionPerformed
      
      if(fileSaved==false)
      {
          exitOperation();
      }
    }//GEN-LAST:event_exitMenuActionPerformed

    private void findMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findMenuActionPerformed
        find=JOptionPane.showInputDialog("Enter text to search.");
        searchText(find);
        
    }//GEN-LAST:event_findMenuActionPerformed

    private void undoMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoMenuActionPerformed
        if(txtContent.getText().length()>0)
        {
            undo();
        }
        
    }//GEN-LAST:event_undoMenuActionPerformed

    private void redoMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redoMenuActionPerformed
        if(txtContent.getText().length()<curContentLen)
        {
            redo();
        }
        
    }//GEN-LAST:event_redoMenuActionPerformed

    private void cutMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cutMenuActionPerformed
            txtContent.cut();
    }//GEN-LAST:event_cutMenuActionPerformed

    private void copyMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyMenuActionPerformed
            txtContent.copy();        // TODO add your handling code here:
    }//GEN-LAST:event_copyMenuActionPerformed

    private void speechSelectedMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_speechSelectedMenuActionPerformed
             voiceData=txtContent.getSelectedText();
            if(voiceData!=null)
            {
                if(thread==null)
                {
                    thread=new Thread(this);
                    thread.start();
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Stop previous speech first.");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "No text selected.");
            }
    }//GEN-LAST:event_speechSelectedMenuActionPerformed
    @Override
    public void run()
    {
        while(thread!=null)
        {
            speech(voiceData);
        }
    }
    
    
    private void speechAllMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_speechAllMenuActionPerformed
             voiceData=txtContent.getText();
            if(voiceData.length()>0)
            {
               if(thread==null)
                {
                    thread=new Thread(this);
                    thread.start();
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Stop previous speech first.");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Empty file.");
            }
    }//GEN-LAST:event_speechAllMenuActionPerformed

    private void wordWrapMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wordWrapMenuActionPerformed
                if(wordWrapMenu.getState()==true)
                {
                   // txtContent.
                    txtContent.setWrapStyleWord(true);
                    txtContent.setLineWrap(true);
                }
                else
                {
                    txtContent.setWrapStyleWord(false);
                    txtContent.setLineWrap(false);
                }
               
    }//GEN-LAST:event_wordWrapMenuActionPerformed

    private void customColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customColorActionPerformed
        Color initialcolor=Color.RED;    
        Color color=JColorChooser.showDialog(this,"Select a color",initialcolor);     
        txtContent.setForeground(color);
    }//GEN-LAST:event_customColorActionPerformed

    private void fontSizeMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fontSizeMenuActionPerformed
        
            
             
    }//GEN-LAST:event_fontSizeMenuActionPerformed

    private void customSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customSizeActionPerformed
            try
        {  
            defaultFontSize=Integer.parseInt(JOptionPane.showInputDialog(this,"Enter font size:(Current size : "+defaultFontSize+" )"));
             setFontSize(defaultFontSize);
                }
        catch(NumberFormatException e)
        {
            JOptionPane.showMessageDialog(this, "Font size value must be numeric.");
        }      // TODO add your handling code here:
    }//GEN-LAST:event_customSizeActionPerformed

    private void customFontStyleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customFontStyleActionPerformed
            txt=(String) JOptionPane.showInputDialog(this,"Select Font Style:","Font Style",JOptionPane.PLAIN_MESSAGE,null,fontStyles,fontStyles[defaultFontStyle]);
            switch(txt)
            {
                case "Normal":{defaultFontStyle=0 ;break;}
                case "Italic":{defaultFontStyle=2 ;break;}
                case "Bold":{defaultFontStyle=1 ;break;}
                default:{defaultFontStyle=3 ;break;}
            }
            setFontStyle(defaultFontStyle);
          
    }//GEN-LAST:event_customFontStyleActionPerformed

    private void customFontFamilyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customFontFamilyActionPerformed
            defaultFontFamily=(String) JOptionPane.showInputDialog(this,"Select Font Style:","Font Style",JOptionPane.PLAIN_MESSAGE,null,fontFamily,fontFamily[getFontFamilyIndex(defaultFontFamily)]);
            setFontFamily(defaultFontFamily);
          
    }//GEN-LAST:event_customFontFamilyActionPerformed

    private void stsBarMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stsBarMenuActionPerformed
                if(stsBarMenu.getState()==true)
                {
                    pnlStsBar.setVisible(true);
                }
                else
                {
                    pnlStsBar.setVisible(false);
                }
    }//GEN-LAST:event_stsBarMenuActionPerformed

    private void selectAllMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAllMenuActionPerformed
            txtContent.selectAll();        // TODO add your handling code here:
    }//GEN-LAST:event_selectAllMenuActionPerformed

    private void stopSpeakMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopSpeakMenuActionPerformed
               if(thread!=null)
               {
                   
                   thread.stop();
                   thread=null;
                   voice.deallocate();
               }
    }//GEN-LAST:event_stopSpeakMenuActionPerformed

    private void playSpeakMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playSpeakMenuActionPerformed
                if(thread!=null)
                {
                    thread.resume();
                }
    }//GEN-LAST:event_playSpeakMenuActionPerformed

    private void pauseSpeakMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseSpeakMenuActionPerformed
            if(thread!=null)
            {
                thread.suspend();
                
            }
    }//GEN-LAST:event_pauseSpeakMenuActionPerformed

    private void customBgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customBgActionPerformed
        Color initialcolor=txtContent.getBackground();    
        Color color=JColorChooser.showDialog(this,"Select a color",initialcolor);     
        txtContent.setBackground(color);
    }//GEN-LAST:event_customBgActionPerformed

    private void blackBGMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blackBGMenuActionPerformed
            txtContent.setBackground(Color.black);
        txtContent.setForeground(Color.white);        // TODO add your handling code here:
    }//GEN-LAST:event_blackBGMenuActionPerformed

    private void whiteBGMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_whiteBGMenuActionPerformed
        txtContent.setBackground(Color.white);
        txtContent.setForeground(Color.black);        // TODO add your handling code here:
    }//GEN-LAST:event_whiteBGMenuActionPerformed

    private void customFGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customFGActionPerformed
        Color initialcolor=Color.RED;    
        Color color=JColorChooser.showDialog(this,"Select a color",initialcolor);     
        txtContent.setForeground(color);        // TODO add your handling code here:
    }//GEN-LAST:event_customFGActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
            new AboutDeveloper().setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void goToMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goToMenuActionPerformed
           try
           {
               start=Integer.parseInt(JOptionPane.showInputDialog("Enter line number:"));
               txtContent.setCaretPosition(txtContent.getDocument().getDefaultRootElement().getElement((start-1)).getStartOffset());
           }
           catch(NumberFormatException e)
           {
               JOptionPane.showMessageDialog(this, "Line number must be numeric value.");
           }
    }//GEN-LAST:event_goToMenuActionPerformed

    private void replaceMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_replaceMenuActionPerformed
            replace=JOptionPane.showInputDialog("Replace with ?");
            if(txtContent.getSelectedText()!=null)
            {
                txtContent.replaceSelection(replace);
            }
            else if(replace.length()>0)
            {
                replaceText(replace);
            }
    }//GEN-LAST:event_replaceMenuActionPerformed

    private void deleteMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteMenuActionPerformed

    private void txtContentKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContentKeyPressed

        fileSaved=false;
        highlighter.removeAllHighlights();
    }//GEN-LAST:event_txtContentKeyPressed

    private void txtContentCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtContentCaretUpdate
        try {

            str.delete(0, str.length());
            str.append("*").append(fileName);

            loadTitleContent(str.toString());

            int caretpos = txtContent.getCaretPosition();
            LineCount = txtContent.getLineOfOffset(caretpos);
            colCount = caretpos - txtContent.getLineStartOffset(LineCount);
            LineCount++;
            if (txtContent.getText().length() == 0) {
                LineCount = 0;
                colCount = 0;
            }
            lblLineColCount.setText("||  Ln " + LineCount + ", Col " + colCount);

            //update content length for redo operation
            prevContentLen=curContentLen;
            curContentLen=txtContent.getText().length();
            if(curContentLen<prevContentLen)
            {
                curContentLen=prevContentLen;
            }

        } catch (BadLocationException ex) {
            Logger.getLogger(TextPadWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_txtContentCaretUpdate

    private void findNextMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findNextMenuActionPerformed
            if(findNextCounter<findNextData.size())
            {
                findNextFun(findNextCounter);
                findNextCounter++;
            }
            else
            {
                findNextCounter=0;
            }
    }//GEN-LAST:event_findNextMenuActionPerformed
    private int getFontFamilyIndex(String item)
    {
        int i,index=0;
        for(i=0;i<fontFamily.length;i++)
        {
            if(fontFamily[i].equals(item))
            {
                index=i;
                break;
            }
        }
        return index ;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
  
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TextPadWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TextPadWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TextPadWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TextPadWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TextPadWindow(1).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem blackBGMenu;
    private javax.swing.JMenuItem copyMenu;
    private javax.swing.JMenuItem customBg;
    private javax.swing.JMenuItem customColor;
    private javax.swing.JMenuItem customFG;
    private javax.swing.JMenuItem customFontFamily;
    private javax.swing.JMenuItem customFontStyle;
    private javax.swing.JMenuItem customSize;
    private javax.swing.JMenuItem cutMenu;
    private javax.swing.JMenuItem deleteMenu;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenu;
    private javax.swing.JMenuItem findMenu;
    private javax.swing.JMenuItem findNextMenu;
    private javax.swing.JMenu fontColorMenu;
    private javax.swing.JMenu fontFamilyMenu;
    private javax.swing.JMenu fontMenu;
    private javax.swing.JMenu fontSizeMenu;
    private javax.swing.JMenu fontStyleMenu;
    private javax.swing.JMenuItem goToMenu;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JLabel lblFind;
    private javax.swing.JLabel lblLineColCount;
    private javax.swing.JMenuItem newMenu;
    private javax.swing.JMenuItem openMenu;
    private javax.swing.JMenuItem pasteMenu;
    private javax.swing.JMenuItem pauseSpeakMenu;
    private javax.swing.JMenuItem playSpeakMenu;
    private javax.swing.JPanel pnlParent;
    private javax.swing.JPanel pnlStsBar;
    private javax.swing.JMenuItem printMenu;
    private javax.swing.JMenuItem redoMenu;
    private javax.swing.JMenuItem replaceMenu;
    private javax.swing.JMenuItem saveAsMenu;
    private javax.swing.JMenu saveMenu;
    private javax.swing.JMenuItem selectAllMenu;
    private javax.swing.JMenuItem speechAllMenu;
    private javax.swing.JMenu speechMenu;
    private javax.swing.JMenuItem speechSelectedMenu;
    private javax.swing.JMenuItem stopSpeakMenu;
    private javax.swing.JCheckBoxMenuItem stsBarMenu;
    private javax.swing.JTextArea txtContent;
    private javax.swing.JMenuItem undoMenu;
    private javax.swing.JMenu viewMenu;
    private javax.swing.JMenuItem whiteBGMenu;
    private javax.swing.JCheckBoxMenuItem wordWrapMenu;
    // End of variables declaration//GEN-END:variables
}
