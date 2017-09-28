python () {
    if d.getVar("TARGET_ARCH").find("riscv") >= 0:
        d.setVar("GLIBC_GIT_URI", "git://github.com/riscv/riscv-glibc")
        d.setVar("SRCBRANCH", "riscv-glibc-2.26")
        d.setVar("SRCREV", "e6a625aceade0948d1c1452006943a747e585bfc")
}

EXTRA_OECONF_remove = "--enable-add-ons"

SELECTED_OPTIMIZATION_append = "-Wno-error"
