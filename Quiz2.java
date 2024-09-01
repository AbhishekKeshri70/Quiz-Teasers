import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Quiz2 implements ActionListener{
   String [] questions= {
                          "Which country will host 2024 Cricket Women's World Cup?",
                          "What is the tallest mountain in the world?",
                          "Which planet is known as the \"Red Planet\"?",
                          "Which of the following planets is not a gas giant?",
                          "In what year was the first international modern Olympiad held?"
                        };
   String [][] options=  {
           {"India","Australia","England","Bangladesh"},
           {"Mount Everest","K2","Denali","Mount Kanajanganga"},
           {"Jupiter","Mars","Venus","Mercury"},
           {"Neptune","Earth","Jupiter","Saturn"},
           {"1897","1896","1902","1903"}
                         };
   char[] answers={ 'C','A','C','C','B'};
   char guess,answer;
   int index,result;
   int correct_guess=0;
   int total_question= questions.length;
   int seconds=10;
   JFrame frame=new JFrame();
   JPanel panel=new JPanel();
   JTextField textField=new JTextField();
   JTextArea textArea=new JTextArea();
   JButton buttonA=new JButton();
    JButton buttonB=new JButton();
    JButton buttonC=new JButton();
    JButton buttonD=new JButton();
    JLabel answer_labelA=new JLabel();
    JLabel answer_labelB=new JLabel();
    JLabel answer_labelC=new JLabel();
    JLabel answer_labelD=new JLabel();
    JLabel timer=new JLabel();
    JLabel second_left=new JLabel();
    JTextField number_right=new JTextField();
    JTextField percentage=new JTextField();

    Timer timer2=new Timer(1000, new ActionListener() {   // For timer
        @Override
        public void actionPerformed(ActionEvent e) {
           seconds--;
           second_left.setText(String.valueOf(seconds));     // Convert second into string
           if(seconds<=0)
           {
               displayAnswer();
           }

        }
    }) ;
    public Quiz2()
    {
       frame.setSize(650,650);
       frame.setResizable(false);
       frame.getContentPane().setBackground(new Color(50,50,50));
       frame.setLayout(null);

       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       textField.setBounds(0,0,650,50);
       textField.setBackground(new Color(25,28,25));
       textField.setForeground(new Color(25,255,0));
       textField.setFont(new Font("MV Boli",Font.ROMAN_BASELINE,30));
       textField.setBorder(BorderFactory.createBevelBorder(1));
       textField.setHorizontalAlignment(JTextField.CENTER);
       textField.setEditable(false);
       frame.add(textField);

        textArea.setBounds(0,50,650,50);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(new Color(25,28,25));
        textArea.setForeground(new Color(25,255,0));
        textArea.setFont(new Font("MV Boli",Font.ROMAN_BASELINE,20));
        textArea.setBorder(BorderFactory.createBevelBorder(1));
        textArea.setEditable(false);

        buttonA.setBounds(0,100,100,100);
        buttonA.setFont(new Font("MV Boli",Font.BOLD,35));
        buttonA.setFocusable(false);
        buttonA.setText("A");
        buttonA.addActionListener(this);

        buttonB.setBounds(0,200,100,100);
        buttonB.setFont(new Font("MV Boli",Font.BOLD,35));
        buttonB.setFocusable(false);
        buttonB.setText("B");
        buttonB.addActionListener(this);

        buttonC.setBounds(0,300,100,100);
        buttonC.setFont(new Font("MV Boli",Font.BOLD,35));
        buttonC.setFocusable(false);
        buttonC.setText("C");
        buttonC.addActionListener(this);

        buttonD.setBounds(0,400,100,100);
        buttonD.setFont(new Font("MV Boli",Font.BOLD,35));
        buttonD.setFocusable(false);
        buttonD.setText("D");
        buttonD.addActionListener(this);

        answer_labelA.setBounds(125,100,500,100);
        answer_labelA.setBackground(new Color(50,50,50));
        answer_labelA.setForeground(new Color(25,255,0));
        answer_labelA.setFont(new Font("Bazooka",Font.PLAIN,35));

        answer_labelB.setBounds(125,200,500,100);
        answer_labelB.setBackground(new Color(50,50,50));
        answer_labelB.setForeground(new Color(25,255,0));
        answer_labelB.setFont(new Font("Bazooka",Font.PLAIN,35));

        answer_labelC.setBounds(125,300,500,100);
        answer_labelC.setBackground(new Color(50,50,50));
        answer_labelC.setForeground(new Color(25,255,0));
        answer_labelC.setFont(new Font("Bazooka",Font.PLAIN,35));

        answer_labelD.setBounds(125,400,500,100);
        answer_labelD.setBackground(new Color(50,50,50));
        answer_labelD.setForeground(new Color(25,255,0));
        answer_labelD.setFont(new Font("Engineer Hand",Font.PLAIN,35));

        second_left.setBounds(535,510,100,100);
        second_left.setBackground(Color.GRAY);
        second_left.setForeground(new Color(255,0,0));
        second_left.setFont(new Font("Ink Free",Font.ITALIC,50));
        second_left.setBorder(BorderFactory.createBevelBorder(1));
        second_left.setHorizontalAlignment(JTextField.CENTER);
        second_left.setText(String.valueOf(seconds));

        number_right.setBounds(225,225,200,100);
        number_right.setBackground(Color.GRAY);
        number_right.setForeground(new Color(25,250,0));
        number_right.setFont(new Font("Ink Free",Font.ITALIC,50));
        number_right.setBorder(BorderFactory.createBevelBorder(1));
        number_right.setHorizontalAlignment(JTextField.CENTER);
        number_right.setEditable(false);

       percentage.setBounds(225,325,200,100);
       percentage.setBackground(Color.GRAY);
        percentage.setForeground(new Color(25,250,0));
        percentage.setFont(new Font("Ink Free",Font.ITALIC,50));
        percentage.setBorder(BorderFactory.createBevelBorder(1));
        percentage.setHorizontalAlignment(JTextField.CENTER);
        percentage.setEditable(false);

        frame.add(textArea);
        frame.add(buttonA);
        frame.add(buttonB);
        frame.add(buttonC);
        frame.add(buttonD);
        frame.add(answer_labelA);
        frame.add(answer_labelB);
        frame.add(answer_labelC);
        frame.add(answer_labelD);
        frame.add(second_left);
        //frame.add(number_right);
       // frame.add(percentage);
        frame.setVisible(true);

        nextQuestion(); // So that next question will call
    }
    public void nextQuestion()
    {
       if(index>=total_question)
       {
           results();
       }
       else {
           textField.setText("Question "+(index+1));
           textArea.setText(questions[index]); // for every question's options.
           answer_labelA.setText(options[index][0]);
           answer_labelB.setText(options[index][1]);
           answer_labelC.setText(options[index][2]);
           answer_labelD.setText(options[index][3]);
           timer2.start();
       }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if(e.getSource()==buttonA)
        {
            answer='A';
            if(answer==answers[index])
            {
                correct_guess++;
            }
        }

        if(e.getSource()==buttonB)
        {
            answer='B';
            if(answer==answers[index])
            {
                correct_guess++;
            }
        }

        if(e.getSource()==buttonC)
        {
            answer='C';
            if(answer==answers[index])
            {
                correct_guess++;
            }
        }

        if(e.getSource()==buttonD)
        {
            answer='D';
            if(answer==answers[index])
            {
                correct_guess++;
            }
        }
        displayAnswer();
    }
    public void displayAnswer()
    {
        timer2.start();           // timer will stop after every question
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if(answers[index]!= 'A')
            answer_labelA.setForeground(Color.red);
        if(answers[index]!= 'B')
            answer_labelB.setForeground(Color.red);
        if(answers[index]!= 'C')
            answer_labelC.setForeground(Color.red);
        if(answers[index]!= 'D')
            answer_labelD.setForeground(Color.red);

        Timer pause=new Timer(2000, new ActionListener() {           // This is for delay in popping next question
            @Override
            public void actionPerformed(ActionEvent e) {
                answer_labelA.setForeground(new Color(25,255,0));
                answer_labelB.setForeground(new Color(25,255,0));
                answer_labelC.setForeground(new Color(25,255,0));
                answer_labelD.setForeground(new Color(25,255,0));

                answer=' ';
                seconds=10;
                second_left.setText(String.valueOf(seconds));
                buttonA.setEnabled(true);
                buttonB.setEnabled(true);
                buttonC.setEnabled(true);
                buttonD.setEnabled(true);
                index++;
                nextQuestion();
            }
        }) ;
        pause.setRepeats(false);
        pause.start();
    }
    public void results()
    {
        timer2.stop();
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

      //  result=(int)(correct_guess/(double)total_question)*100;
       // int result2=(result*100);
        textField.setText("RESULTS");
        textArea.setText("");
        answer_labelA.setText("");
        answer_labelB.setText("");
        answer_labelC.setText("");
        answer_labelD.setText("");

        number_right.setText(correct_guess+"/"+total_question);
      //  percentage.setText(result+"%");

       // frame.add(percentage);
        frame.add(number_right);

    }
}
