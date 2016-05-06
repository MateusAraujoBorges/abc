/* signal.h: The ABC representation of standard C library .
 * Signal handling.
 * Based on C11 Standard 7.14.
 */
#ifndef _SIGNAL_
#define _SIGNAL_

/* Types */
typedef int sig_atomic_t;

/* Macros */
#define SIGABRT 2
#define SIGFPE  3
#define SIGILL  4
#define SIGINT  5
#define SIGSEGV 6
#define SIGTERM 7

#define SIG_DFL (void (*)())0
#define SIG_ERR (void (*)())-1
#define SIG_IGN (void (*)())1

/* Functions */
int raise(int sig);
void (*signal(int sig, void (*func)(int)))(int);

#endif
