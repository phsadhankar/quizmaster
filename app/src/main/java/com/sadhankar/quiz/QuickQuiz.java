package com.sadhankar.quiz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import othree.quiz_master.R;

public class QuickQuiz extends AppCompatActivity {
    TextView question;
    TextView aText;
    TextView bText;
    TextView cText;
    TextView dText;
    FloatingActionButton fab;
    ScrollView scroll;
    RadioButton aRadio;
    RadioButton bRadio;
    RadioButton cRadio;
    RadioButton dRadio;
    int currentQuestion;
    TextView questionNumber;
    ArrayList<Questions> questions;
    String selectedOption = "";
    String n;
    int questionCOrrect;
    String m;
    ArrayList<Integer> chosenNumbers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quick_main);
        Bundle recieved = getIntent().getExtras();
        chosenNumbers = new ArrayList<>();
        questionCOrrect = 0;
        n = recieved.getString("question");
    //    Toast.makeText(getApplicationContext(), n, Toast.LENGTH_LONG).show();
        m = n.substring(n.lastIndexOf("/") + 1, n.indexOf(".quizfile"));
        currentQuestion = 1;
        getSupportActionBar().setTitle("" + m);
        question = (TextView) findViewById(R.id.textView4);
        aText = (TextView) findViewById(R.id.aText);
        bText = (TextView) findViewById(R.id.bText);
        cText = (TextView) findViewById(R.id.cText);
        dText = (TextView) findViewById(R.id.dText);
        scroll = (ScrollView) findViewById(R.id.scrollView2);
        questionNumber = (TextView) findViewById(R.id.questionNumber);
        aRadio = (RadioButton) findViewById(R.id.aRadio);

        bRadio = (RadioButton) findViewById(R.id.bRadio);
        cRadio = (RadioButton) findViewById(R.id.cRadio);
        dRadio = (RadioButton) findViewById(R.id.dRadio);
        fab = (FloatingActionButton) findViewById(R.id.nextfab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedOption.equals("")) {
                    Toast.makeText(QuickQuiz.this,"Please Choose An Option",Toast.LENGTH_SHORT).show();
                } else {


                    if (selectedOption.equals(corretoption)) {
                        fab.setClickable(false);

                        Snackbar.make(scroll, "CORRECT", Snackbar.LENGTH_INDEFINITE)
                                .setAction("Continue", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        questionCOrrect++;
                                        clearPreviousselections();
                                        if (apocalypse == false)
                                            setCurrentQuestion();
                                        else {
                                            AlertDialog.Builder score = new AlertDialog.Builder(QuickQuiz.this).setCancelable(false);
                                            score.setTitle("QUIZ FINISHED!!!");
                                            score.setMessage("You Got " + questionCOrrect + " Questions Of " + LastNumber + " Right");
                                            score.setIcon(android.R.drawable.ic_dialog_info);
                                            score.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    finish();
                                                }
                                            });
                                            score.setNeutralButton("SHARE RESULT", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Intent intent = new Intent(Intent.ACTION_SEND);
                                                    intent.putExtra(Intent.EXTRA_TEXT, "I got " + questionCOrrect + " Of " + LastNumber + " Right On Quiz Master's "
                                                            + m + " Quiz\nHow well can you do?\nDownload the App @ http://www.linktodownload.com");
                                                    intent.setType("text/plain");
                                                    finish();
                                                    startActivity(intent);

                                                }
                                            });
                                            score.show();
                                            score.setCancelable(false);
                                            score.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                @Override
                                                public void onCancel(DialogInterface dialog) {
                                                    finish();
                                                }
                                            });
                                            score.setOnKeyListener(new DialogInterface.OnKeyListener() {
                                                @Override
                                                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {

                                                    finish();



                                                    return true;
                                                }
                                            });
                                            score.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                @Override
                                                public void onDismiss(DialogInterface dialog) {
                                                    finish();
                                                }
                                            });


                                        }
                                        fab.setClickable(true);
                                    }
                                }).show();

                    } else {
                        fab.setClickable(false);
                        Snackbar.make(scroll, "WRONG, CORRECT ANSWER :" + corretoption, Snackbar.LENGTH_INDEFINITE)
                                .setAction("Continue", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        clearPreviousselections();
                                        if (apocalypse == false)
                                            setCurrentQuestion();
                                        else {
                                            AlertDialog.Builder score = new AlertDialog.Builder(QuickQuiz.this).setCancelable(false);
                                            score.setTitle("QUIZ FINISHED!!!");
                                            score.setMessage("You Got " + questionCOrrect + " Questions Of " + LastNumber + " Right");
                                            score.setIcon(android.R.drawable.ic_dialog_info);
                                            score.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    finish();
                                                }
                                            });
                                            score.setNeutralButton("SHARE RESULT", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Intent intent = new Intent(Intent.ACTION_SEND);
                                                    intent.putExtra(Intent.EXTRA_TEXT, "I got " + questionCOrrect + " Of " + LastNumber + " Right On Quiz Master's "
                                                            + m + " Quiz\nHow well can you do?\nDownload the App @ http://www.linktodownload.com");
                                                    intent.setType("text/plain");
                                                    finish();
                                                    startActivity(intent);
                                                }
                                            });


                                            score.show();
                                            score.setCancelable(false);
                                            score.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                @Override
                                                public void onCancel(DialogInterface dialog) {
                                                    finish();
                                                }
                                            });
                                            score.setOnKeyListener(new DialogInterface.OnKeyListener() {
                                                @Override
                                                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {

                                                    finish();


                                                    return true;
                                                }
                                            });



                                        }
                                        fab.setClickable(true);
                                    }
                                }).show();

                    }


                }
            }

        });



        setListeners();
        setListenersForTexts();

        try {
            startQuiz(n);
        }
        catch (IndexOutOfBoundsException t){
            finish();
            Toast.makeText(getApplicationContext(),"Empty Quiz File",Toast.LENGTH_LONG).show();
        }
    }

    private void clearPreviousselections() {
        aRadio.setChecked(false);

        selectedOption = "";
        bRadio.setChecked(false);
        cRadio.setChecked(false);
        dRadio.setChecked(false);
    }
    ArrayList<Questions> Allquestions;
    int LastNumber = 0;
    private void startQuiz(String fileName) {

        Allquestions = new ArrayList<>();
        Workbook book = null;
        try {
            book = new HSSFWorkbook(new FileInputStream(fileName));

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(QuickQuiz.this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        Sheet sheet = book.getSheetAt(0);
        int counter = 1;
        for (Row row : sheet) {

            Questions q = new Questions();
            row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
            q.setQuestion(row.getCell(0).getStringCellValue());
            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
            q.setAText(row.getCell(1).getStringCellValue());
            row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
            q.setBText(row.getCell(2).getStringCellValue());
            row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
            q.setCText(row.getCell(3).getStringCellValue());
            row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
            q.setDText(row.getCell(4).getStringCellValue());
            row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
            q.setAnswer(row.getCell(5).getStringCellValue());
            q.setQuestionNumber(counter);

            Allquestions.add(q);
            counter++;
            LastNumber = counter;


        }

      setCurrentQuestion();

    }

    private void setListenersForTexts() {
        aText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aRadio.setChecked(true);
                selectedOption = "A";
                bRadio.setChecked(false);
                cRadio.setChecked(false);
                dRadio.setChecked(false);
            }
        });
        bText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bRadio.setChecked(true);
                selectedOption = "B";
                aRadio.setChecked(false);
                cRadio.setChecked(false);
                dRadio.setChecked(false);
            }
        });
        cText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cRadio.setChecked(true);
                selectedOption = "C";
                bRadio.setChecked(false);
                aRadio.setChecked(false);
                dRadio.setChecked(false);
            }
        });
        dText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dRadio.setChecked(true);
                selectedOption = "D";
                bRadio.setChecked(false);
                cRadio.setChecked(false);
                aRadio.setChecked(false);
            }
        });
    }

    private void setListeners() {
        aRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOption = "A";
                bRadio.setChecked(false);
                cRadio.setChecked(false);
                dRadio.setChecked(false);
            }
        });
        bRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOption = "B";
                aRadio.setChecked(false);
                cRadio.setChecked(false);
                dRadio.setChecked(false);
            }
        });
        cRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOption = "C";
                bRadio.setChecked(false);
                aRadio.setChecked(false);
                dRadio.setChecked(false);
            }
        });
        dRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOption = "D";
                bRadio.setChecked(false);
                cRadio.setChecked(false);
                aRadio.setChecked(false);
            }
        });

    }
    EditText text;
    public void setCurrentQuestion() {
         text = new EditText(QuickQuiz.this);
        text.setInputType(InputType.TYPE_CLASS_NUMBER);
        AlertDialog.Builder choose = new AlertDialog.Builder(QuickQuiz.this)
                .setCancelable(false)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("Pick a question number ")
                .setView(text)
                .setMessage("Choose a number between 1 & "+LastNumber);
                choose.setPositiveButton("SHOW QUESTION", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        currentQuestion = Integer.parseInt(text.getText().toString());
                        if (currentQuestion > LastNumber||currentQuestion==0) {
                            Toast.makeText(QuickQuiz.this,"Invalid question number",Toast.LENGTH_SHORT).show();
                            setCurrentQuestion();
                        } else if (!chosenNumbers.contains(currentQuestion)) {
                            chosenNumbers.add(currentQuestion);
                            Toast.makeText(QuickQuiz.this, "Question " + text.getText().toString(), Toast.LENGTH_LONG).show();
                            startThis();
                        } else {
                            String remaining = "";
                            for (int i = 1; i < LastNumber; i++) {

                                if (!chosenNumbers.contains(i)) {
                                    remaining += (i) + ", ";
                                }

                            }
                            Toast.makeText(QuickQuiz.this, "Number already taken choose from " + remaining, Toast.LENGTH_LONG).show();
                            setCurrentQuestion();
                        }

                    }
                });

        choose.setNegativeButton("END QUIZ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                AlertDialog.Builder score = new AlertDialog.Builder(QuickQuiz.this);
                score.setTitle("QUIZ FINISHED!!!");
                score.setMessage("You Got " + questionCOrrect + " Questions Of " + LastNumber + " Right");
                score.setIcon(android.R.drawable.ic_dialog_info);
                score.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                score.setNeutralButton("SHARE RESULT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.putExtra(Intent.EXTRA_TEXT, "I got " + questionCOrrect + " Of " + LastNumber + " Right On Quiz Master's "
                                + m + " Quiz\nHow well can you do?\nDownload the App @ http://www.linktodownload.com");
                        intent.setType("text/plain");
                        finish();
                        startActivity(intent);

                    }
                });
                score.show();
            }
        });

        choose.show();



    }

    String corretoption ="";
    boolean apocalypse = false;
    private void startThis() {




        int arraycount = currentQuestion-1;

        if(chosenNumbers.size()==LastNumber){
            // fab.setImageIcon(Icon.createWithResource(getApplicationContext(),R.mipmap.ic_finished));
            apocalypse = true;
        }
        try {
            questionNumber.setText(String.valueOf(currentQuestion));
            question.setText(Allquestions.get(arraycount).getQuestion());
            aText.setText(Allquestions.get(arraycount).getAText());
            bText.setText(Allquestions.get(arraycount).getBText());
            cText.setText(Allquestions.get(arraycount).getCText());
            dText.setText(Allquestions.get(arraycount).getDText());
            corretoption = Allquestions.get(arraycount).getAnswer();
        }catch (IndexOutOfBoundsException e){

            finish();

        }
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder n = new AlertDialog.Builder(QuickQuiz.this);
        n.setTitle("QUIT QUIZ?");
        n.setMessage("Are you sure you want to quit this quiz?");
        n.setIcon(android.R.drawable.ic_dialog_alert);
        n.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent upIntent = NavUtils.getParentActivityIntent(QuickQuiz.this);
                NavUtils.navigateUpTo(QuickQuiz.this, upIntent);
            }
        });
        n.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        n.show();


    }@Override
     public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                AlertDialog.Builder n = new AlertDialog.Builder(QuickQuiz.this);
                n.setTitle("QUIT QUIZ?");
                n.setMessage("Are you sure you want to quit this quiz?");
                n.setIcon(android.R.drawable.ic_dialog_alert);

                n.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent create = new Intent(QuickQuiz.this, ChooseQuiz.class);
                        Bundle extra = new Bundle();
                        extra.putString("type", "quick");
                        create.putExtras(extra);


                        startActivityForResult(create,0);
                        //
                    }
                });
                n.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                n.show();

//
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode ==RESULT_OK){

            Bundle result = data.getExtras();
            String s = result.getString("result");
            String t = result.getString("type");

            startQuiz(s,t);

        }

    }
    private void startQuiz(String s, String t) {

        if(t.equals("quick")){

            Intent create = new Intent(QuickQuiz.this,QuickQuiz.class);
            finish();
            Bundle extra = new Bundle();
            extra.putString("question", s);
            create.putExtras(extra);
            startActivity(create);

        }

    }

}
