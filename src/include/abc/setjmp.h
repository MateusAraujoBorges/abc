/* setjmp.h: The ABC representation of standard C library setjmp.
 * Nonlocal jumps <setjmp.h> 7.13.
 * Based on C11 Standard.
 */
#ifndef _SETJMP_
#define _SETJMP_

typedef int jmp_buf[];

/* Functions */

int setjmp(jmp_buf);
void longjmp(jmp_buf, int);

#endif
