#ifndef _STDARG_
#define _STDARG_

/* Types */
typedef struct {int id;} va_list;

typedef struct {int id;} _va_arg_return_t;

typedef struct {int id;} _va_param;

_va_arg_return_t _va_arg(va_list val);

/* Macros */
#define va_arg(ap, type) ((type)_va_arg(ap))

void va_copy(va_list dest, va_list src);

void va_end(va_list ap);

void _va_start(va_list ap, _va_param ident);

#define va_start(ap, parmN) _va_start(ap, (_va_param)(parmN))

#endif
