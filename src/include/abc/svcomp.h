#ifndef _SVCOMP_
#define _SVCOMP_
#include<gnuc.h>

/**************************** macros *****************************/
#define __const const
#define __inline inline
#define __inline__ inline
#define __restrict restrict
#define __thread _Thread_local
#define __extension__
#define __asm__(X)
// #define __attribute__(X) this couldn't work here
#define __signed__ signed
#define __volatile volatile
#define __PRETTY_FUNCTION__ (void*)0


/**************************** types *****************************/
typedef unsigned long int size_t;


/**************************** functions *****************************/

void __VERIFIER_error(void);
void __VERIFIER_assume(int);
void __VERIFIER_assert(int);
extern $system[stdio] int printf(const char * restrict format, ...);
extern $system[stdlib] void* malloc(size_t size);
extern $system[asserts] void assert(_Bool);
extern $system void assume(_Bool);

int __VERIFIER_nondet_int(void);
unsigned int __VERIFIER_nondet_uint(void);
void* __VERIFIER_nondet_pointer(void);
int __VERIFIER_nondet_bool(void);
int __VERIFIER_nondet_int(void);
long __VERIFIER_nondet_long(void);
unsigned long __VERIFIER_nondet_ulong(void);
char __VERIFIER_nondet_char(void);
double __VERIFIER_nondet_double(void);
float __VERIFIER_nondet_float(void);
#endif
