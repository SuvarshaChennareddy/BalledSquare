/***************************************************************
 * Name:      FakeianoMain.cpp
 * Purpose:   Code for Application Frame
 * Author:     ()
 * Created:   2019-10-02
 * Copyright:  ()
 * License:
 **************************************************************/

#include "FakeianoMain.h"
#include <wx/msgdlg.h>
#include <iostream>
#include <string>
#include <windows.h>
#include <mmsystem.h>
#include <time.h>
#include <Math.h>

//(*InternalHeaders(FakeianoFrame)
#include <wx/font.h>
#include <wx/intl.h>
#include <wx/string.h>
#include <wx/sound.h>
//*)
using namespace std;
//helper functions
enum wxbuildinfoformat {
    short_f, long_f };

wxString wxbuildinfo(wxbuildinfoformat format)
{
    wxString wxbuild(wxVERSION_STRING);

    if (format == long_f )
    {
#if defined(__WXMSW__)
        wxbuild << _T("-Windows");
#elif defined(__UNIX__)
        wxbuild << _T("-Linux");
#endif

#if wxUSE_UNICODE
        wxbuild << _T("-Unicode build");
#else
        wxbuild << _T("-ANSI build");
#endif // wxUSE_UNICODE
    }

    return wxbuild;
}

//(*IdInit(FakeianoFrame)
const long FakeianoFrame::ID_STATICTEXT1 = wxNewId();
const long FakeianoFrame::ID_STATICTEXT2 = wxNewId();
const long FakeianoFrame::ID_TEXTCTRL1 = wxNewId();
const long FakeianoFrame::ID_TEXTCTRL2 = wxNewId();
const long FakeianoFrame::ID_STATICTEXT3 = wxNewId();
const long FakeianoFrame::ID_BUTTON1 = wxNewId();
const long FakeianoFrame::ID_BUTTON2 = wxNewId();
const long FakeianoFrame::ID_BUTTON3 = wxNewId();
const long FakeianoFrame::ID_BUTTON4 = wxNewId();
const long FakeianoFrame::ID_GRID1 = wxNewId();
const long FakeianoFrame::ID_BUTTON5 = wxNewId();
const long FakeianoFrame::idMenuQuit = wxNewId();
const long FakeianoFrame::idMenuAbout = wxNewId();
const long FakeianoFrame::ID_STATUSBAR1 = wxNewId();
//*)

BEGIN_EVENT_TABLE(FakeianoFrame,wxFrame)
    //(*EventTable(FakeianoFrame)
    //*)
END_EVENT_TABLE()

FakeianoFrame::FakeianoFrame(wxWindow* parent,wxWindowID id)
{
    //(*Initialize(FakeianoFrame)
    wxMenu* Menu1;
    wxMenu* Menu2;
    wxMenuBar* MenuBar1;
    wxMenuItem* MenuItem1;
    wxMenuItem* MenuItem2;

    Create(parent, id, _("Fake-iano By Suvarsha Chennareddy"), wxDefaultPosition, wxDefaultSize, wxDEFAULT_FRAME_STYLE, _T("id"));
    SetClientSize(wxSize(635,469));
    SetForegroundColour(wxColour(148,1,148));
    SetBackgroundColour(wxColour(238,155,64));
    StaticText1 = new wxStaticText(this, ID_STATICTEXT1, _("Have fun making music with a fake digital piano..."), wxPoint(192,48), wxDefaultSize, 0, _T("ID_STATICTEXT1"));
    Name = new wxStaticText(this, ID_STATICTEXT2, _("Fake-iano"), wxPoint(240,8), wxDefaultSize, 0, _T("ID_STATICTEXT2"));
    wxFont NameFont(20,wxFONTFAMILY_SWISS,wxFONTSTYLE_NORMAL,wxFONTWEIGHT_BOLD,false,_T("Gadugi"),wxFONTENCODING_DEFAULT);
    Name->SetFont(NameFont);
    ScriptName = new wxTextCtrl(this, ID_TEXTCTRL1, _("Name of Script you are currently editing"), wxPoint(200,80), wxSize(192,20), 0, wxDefaultValidator, _T("ID_TEXTCTRL1"));
    Script = new wxTextCtrl(this, ID_TEXTCTRL2, _("Musical notes/script"), wxPoint(160,104), wxSize(280,24), 0, wxDefaultValidator, _T("ID_TEXTCTRL2"));
    Instructions = new wxStaticText(this, ID_STATICTEXT3, _("Instructions:\n -To use this musical tool, you need to first make a musical piece. To do this just type in the desired name of the piece into the first textbox.\n -To start actually playing, just click on the second textbox and start pressing your keyboard keys (only \"A\" through\"L\").\n -All the buttons below the textbox do the same function as what they are labled as.\n-Note that you cannot do anything when your notes are getting autoplayed.  If you need to stop \"urgently\", then just force close the program. Sorry for the nuisance :( .\n -The table shows all the musical pieces you have created. \n Have fun smahsing keys."), wxPoint(40,304), wxSize(560,120), 0, _T("ID_STATICTEXT3"));
    create = new wxButton(this, ID_BUTTON1, _("Create"), wxPoint(152,168), wxDefaultSize, 0, wxDefaultValidator, _T("ID_BUTTON1"));
    import = new wxButton(this, ID_BUTTON2, _("Import\\Refresh Script"), wxPoint(216,168), wxDefaultSize, 0, wxDefaultValidator, _T("ID_BUTTON2"));
    save = new wxButton(this, ID_BUTTON3, _("Save"), wxPoint(328,168), wxDefaultSize, 0, wxDefaultValidator, _T("ID_BUTTON3"));
    del = new wxButton(this, ID_BUTTON4, _("Delete"), wxPoint(392,168), wxDefaultSize, 0, wxDefaultValidator, _T("ID_BUTTON4"));
    Grid1 = new wxGrid(this, ID_GRID1, wxPoint(168,200), wxSize(264,96), 0, _T("ID_GRID1"));
    Grid1->CreateGrid(25,2);
    Grid1->EnableEditing(true);
    Grid1->EnableGridLines(true);
    Grid1->SetDefaultCellFont( Grid1->GetFont() );
    Grid1->SetDefaultCellTextColour( Grid1->GetForegroundColour() );
    Autoplay = new wxButton(this, ID_BUTTON5, _("Auto-play Notes"), wxPoint(248,136), wxDefaultSize, 0, wxDefaultValidator, _T("ID_BUTTON5"));
    MenuBar1 = new wxMenuBar();
    Menu1 = new wxMenu();
    MenuItem1 = new wxMenuItem(Menu1, idMenuQuit, _("Quit\tAlt-F4"), _("Quit the application"), wxITEM_NORMAL);
    Menu1->Append(MenuItem1);
    MenuBar1->Append(Menu1, _("&File"));
    Menu2 = new wxMenu();
    MenuItem2 = new wxMenuItem(Menu2, idMenuAbout, _("About\tF1"), _("Show info about this application"), wxITEM_NORMAL);
    Menu2->Append(MenuItem2);
    MenuBar1->Append(Menu2, _("Help"));
    SetMenuBar(MenuBar1);
    StatusBar1 = new wxStatusBar(this, ID_STATUSBAR1, 0, _T("ID_STATUSBAR1"));
    int __wxStatusBarWidths_1[1] = { -1 };
    int __wxStatusBarStyles_1[1] = { wxSB_NORMAL };
    StatusBar1->SetFieldsCount(1,__wxStatusBarWidths_1);
    StatusBar1->SetStatusStyles(1,__wxStatusBarStyles_1);
    SetStatusBar(StatusBar1);

    Connect(ID_TEXTCTRL1,wxEVT_COMMAND_TEXT_UPDATED,(wxObjectEventFunction)&FakeianoFrame::OnScriptNameText);
    Connect(ID_TEXTCTRL2,wxEVT_COMMAND_TEXT_UPDATED,(wxObjectEventFunction)&FakeianoFrame::OnScriptText);
    Connect(ID_BUTTON1,wxEVT_COMMAND_BUTTON_CLICKED,(wxObjectEventFunction)&FakeianoFrame::OncreateClick);
    Connect(ID_BUTTON2,wxEVT_COMMAND_BUTTON_CLICKED,(wxObjectEventFunction)&FakeianoFrame::OnimportClick);
    Connect(ID_BUTTON3,wxEVT_COMMAND_BUTTON_CLICKED,(wxObjectEventFunction)&FakeianoFrame::OnsaveClick);
    Connect(ID_BUTTON4,wxEVT_COMMAND_BUTTON_CLICKED,(wxObjectEventFunction)&FakeianoFrame::OndelClick);
    Connect(ID_GRID1,wxEVT_GRID_CELL_LEFT_CLICK,(wxObjectEventFunction)&FakeianoFrame::OnGrid1CellLeftClick);
    Connect(ID_BUTTON5,wxEVT_COMMAND_BUTTON_CLICKED,(wxObjectEventFunction)&FakeianoFrame::OnAutoplayClick);
    Connect(idMenuQuit,wxEVT_COMMAND_MENU_SELECTED,(wxObjectEventFunction)&FakeianoFrame::OnQuit);
    Connect(idMenuAbout,wxEVT_COMMAND_MENU_SELECTED,(wxObjectEventFunction)&FakeianoFrame::OnAbout);
    //*)
    db = new wxSQLite3Database();
    wxString nname = wxGetCwd() + wxT("/database.db");
    db->Open(nname);
    wxString query = wxT("CREATE TABLE IF NOT EXISTS scripts (id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY, name TEXT NOT NULL, notes TEXT NOT NULL);");
    db->ExecuteUpdate(query);
    try{
        wxString indexize = wxT("CREATE UNIQUE INDEX idx_name ON scripts (name);");
        db->ExecuteUpdate(indexize);
    }catch(...){};
    db->Close();
    fillTable();
}
FakeianoFrame::~FakeianoFrame()
{
    //(*Destroy(FakeianoFrame)
    //*)
}
void FakeianoFrame::OnQuit(wxCommandEvent& event)
{
    Close();
}
//bool add = false;
bool isUser = true;
bool dele = false;
wxString dots = wxT("");
wxString lexicon = wxT("ASDFGHJKL");
bool playy = true;
int clo;
void FakeianoFrame::play(wxString note){
    if(lexicon.Contains(note)){
    wxString filepath = wxT("piano\\") + note + wxT(".wav");
    wxSound::Play(filepath, wxSOUND_ASYNC);
    }else{
        wxMilliSleep(500);
    }
}
string toString(wxString wstring){
    string s = string(wstring.mb_str());
    return s;
}
char* toChar(string s){
    char cstr[s.size() + 1];
    s.copy(cstr, s.size() + 1);
    cstr[s.size()] = '\0';
    return cstr;
}
/*
void FakeianoFrame::delay(int seconds)
{
   time_t t1;
   t1 = time(0) + seconds;
   while(  time(0) < t1);
}
*/
void FakeianoFrame::addDots(int milis){
    double div = milis/500;
    int num = floor(div);
    for(int i = 0; i < num; i++)
        dots.Append(wxT("."));
}

void FakeianoFrame::addChars(wxString c){
    isUser = false;
    if(lexicon.Contains(c)){
        play(wxT("L"));
        play(c);
        Script->AppendText(c);
        isUser=true;
        clo = clock();
    }
}

void FakeianoFrame::fillTable()
{
    try{
    StatusBar1->SetStatusText(_("Successful Operation!"));
    //play(wxT("H"));
    wxString nname = wxGetCwd() + wxT("/database.db");
    db->Open(nname);
    Grid1->ClearGrid();
    wxSQLite3ResultSet Res = db->ExecuteQuery(wxT("SELECT * FROM scripts;"));

    int count = 0;

    while(Res.NextRow()){
        //Grid1->SetCellValue(count, 0, Res.GetAsString(0));
        Grid1->SetCellValue(count, 0,Res.GetString(1));
        Grid1->SetCellValue(count, 1,Res.GetAsString(2));
        count++;
    }
    Res.Finalize();
    db->Close();
    }catch(...){
    StatusBar1->SetStatusText(_("Error"));
    }
}

void FakeianoFrame::OnAbout(wxCommandEvent& event)
{
    wxString msg = wxbuildinfo(long_f);
    wxMessageBox(msg, _("Welcome to Fake-iano..."));
}



void FakeianoFrame::OncreateClick(wxCommandEvent& event)
{
    try{
    StatusBar1->SetStatusText(_("Creating..."));
    wxString nname = wxGetCwd() + wxT("/database.db");
    db->Open(nname);
    wxString scriptname = ScriptName->GetValue();
    wxString script = Script->GetValue();
    db->ExecuteUpdate(wxT("INSERT INTO scripts (name, notes) VALUES (\'") + scriptname + wxT("\',\'") + script + wxT("\');"));
    db->Close();
    fillTable();
    }catch(...){
    StatusBar1->SetStatusText(_("Error"));
    }
}
void FakeianoFrame::OnimportClick(wxCommandEvent& event)
{
    try{
    StatusBar1->SetStatusText(_("Importing/Refreshing..."));
    wxString nname = wxGetCwd() + wxT("/database.db");
    db->Open(nname);
    wxString scriptname = ScriptName->GetValue();
    wxSQLite3ResultSet Res = db->ExecuteQuery(wxT("SELECT * FROM scripts WHERE name=\'") + scriptname + wxT("\';"));

    while(Res.NextRow()){
        Script->SetValue(Res.GetAsString(2));
    }
    Res.Finalize();
    db->Close();
    fillTable();
    }catch(...){
    StatusBar1->SetStatusText(_("Error"));
    }
}

void FakeianoFrame::OnsaveClick(wxCommandEvent& event)
{
    try{
    StatusBar1->SetStatusText(_("Saving..."));
    wxString nname = wxGetCwd() + wxT("/database.db");
    db->Open(nname);
    wxString scriptname = ScriptName->GetValue();
    wxString script = Script->GetValue();
    db->ExecuteUpdate(wxT("UPDATE scripts SET notes=\'") + script + wxT("\' WHERE name=\'") + scriptname + wxT("\';"));
    db->Close();
    fillTable();
    }catch(...){
    StatusBar1->SetStatusText(_("Error"));
    }
}

void FakeianoFrame::OndelClick(wxCommandEvent& event)
{
    try{
    if(!dele){
    StatusBar1->SetStatusText(_("Are your sure?"));
    Script->SetValue(wxT("Click again to delete the specified script"));
    dele = true;
    }else{
    StatusBar1->SetStatusText(_("Deleting..."));
    wxString nname = wxGetCwd() + wxT("/database.db");
    db->Open(nname);
    wxString scriptname = ScriptName->GetValue();
    wxString script = Script->GetValue();
    db->ExecuteUpdate(wxT("DELETE FROM scripts WHERE name=\'") + scriptname + wxT("\';"));
    db->Close();
    fillTable();
    dele = false;
    }
    }catch(...){
        StatusBar1->SetStatusText(_("Error"));
    }

}

void FakeianoFrame::OnScriptNameText(wxCommandEvent& event)
{
}


void FakeianoFrame::OnScriptText(wxCommandEvent& event)
{
    //add=false;
    wxString a = Script->GetValue().Right(1).MakeUpper();
    if(Script->IsModified() && isUser){
    if(lexicon.Contains(a)){
    StatusBar1->SetStatusText(_("Composing..."));
    addDots(clock() - clo);
    wxString tmp = Script->GetValue().RemoveLast();
    wxString ch = Script->GetValue().Right(1);
    if (tmp != wxT(""))
    Script->SetValue(tmp + dots);
    else Script->SetValue(wxT(""));
    dots = wxT("");
    addChars(ch.MakeUpper());
    }else if(!lexicon.Contains(a)){
    wxString tmp = Script->GetValue().RemoveLast();
    if (tmp != wxT("")){
    isUser = false;
    Script->Clear();
    Script->AppendText(tmp);
    isUser=true;
    }
    else Script->SetValue(wxT(""));
    }
    }
}


void FakeianoFrame::OnGrid1CellLeftClick(wxGridEvent& event)
{
    StatusBar1->SetStatusText(_("Your Saved Music"));
}
void FakeianoFrame::playNotes(){
    StatusBar1->SetStatusText(_("Auto-playing..."));
    wxString script = Script->GetValue();
    int num = static_cast<int>(script.Length());
    for(int i = 0; i < num; i++){
    play(script.SubString(i,i));
    }
    StatusBar1->SetStatusText(_("Successful Operation!"));
}
void FakeianoFrame::OnAutoplayClick(wxCommandEvent& event)
{
    playNotes();
}

