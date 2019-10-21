/***************************************************************
 * Name:      FakeianoApp.cpp
 * Purpose:   Code for Application Class
 * Author:     ()
 * Created:   2019-10-02
 * Copyright:  ()
 * License:
 **************************************************************/

#include "FakeianoApp.h"

//(*AppHeaders
#include "FakeianoMain.h"
#include <wx/image.h>
//*)

IMPLEMENT_APP(FakeianoApp);

bool FakeianoApp::OnInit()
{
    //(*AppInitialize
    bool wxsOK = true;
    wxInitAllImageHandlers();
    if ( wxsOK )
    {
    	FakeianoFrame* Frame = new FakeianoFrame(0);
    	Frame->Show();
    	SetTopWindow(Frame);
    }
    //*)
    return wxsOK;

}
