#include <Windows.h>

int WINAPI WinMain(HINSTANCE hInstance, HINSTANCE hPrevInstance, LPSTR lpCmdLine, int nShowCmd) {
	WinExec("jdk\\bin\\java.exe -jar \"Game fight.jar\"", SW_HIDE);
	return 0;
}