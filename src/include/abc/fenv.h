/* fenv.h: The ABC representation of standard C library fenv.h.
 * Floating-point environment <fenv.h>.
 * Based on C11 Standard.
 */
#ifndef _FENV_
#define _FENV_

/* MACROS */
#define FE_DIVBYZERO 0
#define FE_INEXACT 1
#define FE_INVALID 2
#define FE_OVERFLOW 3
#define FE_UNDERFLOW 4
#define FE_ALL_EXCEPT 5

#define FE_DOWNWARD 6
#define FE_TONEAREST 7
#define FE_TOWARDZERO 8
#define FE_UPWARD 9

#define FE_DFL_ENV 10

/* TYPES */
typedef struct fenv_t fenv_t;
typedef long int fexcept_t;

/* Functions */
int  feclearexcept(int);
int  fegetexceptflag(fexcept_t *, int);
int  feraiseexcept(int);
int  fesetexceptflag(const fexcept_t *, int);
int  fetestexcept(int);
int  fegetround(void);
int  fesetround(int);
int  fegetenv(fenv_t *);
int  feholdexcept(fenv_t *);
int  fesetenv(const fenv_t *);
int  feupdateenv(const fenv_t *);

#endif
