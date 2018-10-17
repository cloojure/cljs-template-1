(ns flintstones.slate-macros  ; *.cljs file makes macros available to normal CLJS code
  (:require-macros [flintstones.slate-macros]))

;*****************************************************************************
;
; IMPORTANT:  Need an empty `flintstones/slate-macros.cljs` file with (:require-macros ...) as a 
; "hook" so the compiler can find the `flintstones/slate-macros.clj[c]` file containing the macros.  
;
; (MAYBE NOT???) This also allows user code to ignore difference between fn/macro in (:require ...) expression.
;
;*****************************************************************************
