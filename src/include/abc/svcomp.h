#ifndef _SVCOMP_
#define _SVCOMP_
#include<gnuc.h>

/**************************** types *****************************/
// typedef unsigned long int size_t;

/**************************** functions *****************************/
/* EVENTUALLY DELETE THESE:
extern $system[stdio] int printf(const char * restrict format, ...);
extern $system[stdlib] void* malloc(size_t size);
extern $system[asserts] void assert(_Bool);
*/
void __VERIFIER_error(void);
void __VERIFIER_assume(int);
extern void __VERIFIER_atomic_begin();
extern void __VERIFIER_atomic_end();
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
