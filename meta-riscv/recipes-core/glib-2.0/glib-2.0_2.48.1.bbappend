do_compile_append_class-nativesdk () {
 find ${D} -name go
  sed -i 's/Libs.private.*$//' ${B}/gobject-2.0.pc
}
