/***************************************************************
 * Name:      FakeianoMain.h
 * Purpose:   Defines Application Frame
 * Author:     ()
 * Created:   2019-10-02
 * Copyright:  ()
 * License:
 **************************************************************/

#ifndef FAKEIANOMAIN_H
#define FAKEIANOMAIN_H

//(*Headers(FakeianoFrame)
#include <wx/button.h>
#include <wx/frame.h>
#include <wx/grid.h>
#include <wx/menu.h>
#include <wx/stattext.h>
#include <wx/statusbr.h>
#include <wx/textctrl.h>
//*)
#include "wx/wxsqlite3.h"
#include <string>
using namespace std;
class FakeianoFrame: public wxFrame
{
    public:

        FakeianoFrame(wxWindow* parent,wxWindowID id = -1);
        void fillTable();
        //string toString();
        //char* toChar();
        void play(wxString note);
        void addDots(int milis);
        void playNotes();
        //void delay(int seconds);
        void addChars(wxString c);
        virtual ~FakeianoFrame();

    private:

        //(*Handlers(FakeianoFrame)
        void OnQuit(wxCommandEvent& event);
        void OnAbout(wxCommandEvent& event);
        void OncreateClick(wxCommandEvent& event);
        void OnimportClick(wxCommandEvent& event);
        void OnsaveClick(wxCommandEvent& event);
        void OndelClick(wxCommandEvent& event);
        void OnScriptNameText(wxCommandEvent& event);
        void OnScriptText(wxCommandEvent& event);
        void OnGrid1CellLeftClick(wxGridEvent& event);
        void OnPlayClick(wxCommandEvent& event);
        void OnAutoplayClick(wxCommandEvent& event);
        void OnnAutoplayClick(wxCommandEvent& event);
        //*)

        //(*Identifiers(FakeianoFrame)
        static const long ID_STATICTEXT1;
        static const long ID_STATICTEXT2;
        static const long ID_TEXTCTRL1;
        static const long ID_TEXTCTRL2;
        static const long ID_STATICTEXT3;
        static const long ID_BUTTON1;
        static const long ID_BUTTON2;
        static const long ID_BUTTON3;
        static const long ID_BUTTON4;
        static const long ID_GRID1;
        static const long ID_BUTTON5;
        static const long idMenuQuit;
        static const long idMenuAbout;
        static const long ID_STATUSBAR1;
        //*)

        //(*Declarations(FakeianoFrame)
        wxButton* Autoplay;
        wxButton* create;
        wxButton* del;
        wxButton* import;
        wxButton* save;
        wxGrid* Grid1;
        wxStaticText* Instructions;
        wxStaticText* Name;
        wxStaticText* StaticText1;
        wxStatusBar* StatusBar1;
        wxTextCtrl* Script;
        wxTextCtrl* ScriptName;
        //*)
        wxSQLite3Database* db;

        DECLARE_EVENT_TABLE()
};

#endif // FAKEIANOMAIN_H
