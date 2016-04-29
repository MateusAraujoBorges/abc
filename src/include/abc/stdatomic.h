/* stdatomic.h: The ABC representation of standard C library.
 * C11 7.17: The header <stdatomic.h> defines several macros and declares several 
 * types and functions for performing atomic operations on data shared 
 * between threads.
 */
 
#ifndef _STDATOMIC_
#define _STDATOMIC_

#include <stddef.h>
#include <stdint.h>

/* Types */
typedef enum memory_order{ //defines memory ordering constraints 
  memory_order_relaxed,
  memory_order_consume,
  memory_order_acquire,
  memory_order_release,
  memory_order_acq_rel,
  memory_order_seq_cst
}memory_order;

/* lock-free atomic boolean flag */
typedef struct atomic_flag atomic_flag;

typedef unsigned long int char16_t;
typedef unsigned long int char32_t;

/* Atomic types */
typedef _Bool              atomic_bool;
typedef char               atomic_char;
typedef signed char        atomic_schar;
typedef unsigned char      atomic_uchar;
typedef short              atomic_short;
typedef unsigned short     atomic_ushort;
typedef int                atomic_int;
typedef unsigned int       atomic_uint;
typedef long               atomic_long;
typedef unsigned long      atomic_ulong;
typedef long long          atomic_llong;
typedef unsigned long long atomic_ullong;
typedef char16_t           atomic_char16_t;
typedef uint_least32_t     atomic_char32_t;
typedef wchar_t            atomic_wchar_t;
typedef int_least8_t       atomic_int_least8_t;
typedef uint_least8_t      atomic_uint_least8_t;
typedef int_least16_t      atomic_int_least16_t;
typedef uint_least16_t     atomic_uint_least16_t;
typedef int_least32_t      atomic_int_least32_t;
typedef uint_least32_t     atomic_uint_least32_t;
typedef int_least64_t      atomic_int_least64_t;
typedef uint_least64_t     atomic_uint_least64_t;
typedef int_fast8_t        atomic_int_fast8_t;
typedef uint_fast8_t       atomic_uint_fast8_t;
typedef int_fast16_t       atomic_int_fast16_t;
typedef uint_fast16_t      atomic_uint_fast16_t;
typedef int_fast32_t       atomic_int_fast32_t;
typedef uint_fast32_t      atomic_uint_fast32_t;
typedef int_fast64_t       atomic_int_fast64_t;
typedef uint_fast64_t      atomic_uint_fast64_t;
typedef intptr_t           atomic_intptr_t;
typedef uintptr_t          atomic_uintptr_t;
typedef size_t             atomic_size_t;
typedef ptrdiff_t          atomic_ptrdiff_t;
typedef intmax_t           atomic_intmax_t;
typedef uintmax_t          atomic_uintmax_t;

typedef struct A A;
typedef struct C C;
typedef struct M M;

/* Macros */
#define ATOMIC_BOOL_LOCK_FREE       _ABC_ATOMIC_BOOL_LOCK_FREE
#define ATOMIC_CHAR_LOCK_FREE       _ABC_ATOMIC_CHAR_LOCK_FREE
#define ATOMIC_CHAR16_T_LOCK_FREE   _ABC_ATOMIC_CHAR16_T_LOCK_FREE
#define ATOMIC_CHAR32_T_LOCK_FREE   _ABC_ATOMIC_CHAR32_T_LOCK_FREE
#define ATOMIC_WCHAR_T_LOCK_FREE    _ABC_ATOMIC_WCHAR_T_LOCK_FREE
#define ATOMIC_SHORT_T_LOCK_FREE    _ABC_ATOMIC_SHORT_T_LOCK_FREE
#define ATOMIC_INT_T_LOCK_FREE      _ABC_ATOMIC_INT_T_LOCK_FREE
#define ATOMIC_LONG_T_LOCK_FREE     _ABC_ATOMIC_LONG_T_LOCK_FREE
#define ATOMIC_LLONG_T_LOCK_FREE    _ABC_ATOMIC_LLONG_T_LOCK_FREE
#define ATOMIC_POINTER_T_LOCK_FREE  _ABC_ATOMIC_POINTER_T_LOCK_FREE

#define ATOMIC_FLAG_INIT  0

/* Functions */
#define ATOMIC_VAR_INIT(value)
#define kill_dependency(y) 
void atomic_thread_fence(memory_order);
void atomic_signal_fence(memory_order);
_Bool atomic_is_lock_free(const volatile A *);
void atomic_store(volatile A *, C);
void atomic_store_explicit(volatile A *, C, memory_order);
C atomic_load(volatile A *);
C atomic_load_explicit(volatile A *, memory_order);
C atomic_exchange(volatile A *, C);
C atomic_exchange_explicit(volatile A *, C, memory_order);
_Bool atomic_compare_exchange_strong(volatile A *, C *, C );
_Bool atomic_compare_exchange_strong_explicit(volatile A *, C *, C, memory_order, memory_order);
_Bool atomic_compare_exchange_weak(volatile A *, C *, C );
_Bool atomic_compare_exchange_weak_explicit(volatile A *, C *, C, memory_order, memory_order);
C atomic_fetch_key(volatile A *, M);
C atomic_fetch_key_explicit(volatile A *, M, memory_order);
_Bool atomic_flag_test_and_set(volatile atomic_flag *);
_Bool atomic_flag_test_and_set_explicit(volatile atomic_flag *, memory_order);
void atomic_flag_clear(volatile atomic_flag *);
void atomic_flag_clear_explicit(volatile atomic_flag *, memory_order);

#endif
