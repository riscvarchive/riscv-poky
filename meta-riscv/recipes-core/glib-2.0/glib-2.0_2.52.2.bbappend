do_compile_append () {
  sed -i 's/Libs.private.*$//' ${B}/gobject-2.0.pc
}
