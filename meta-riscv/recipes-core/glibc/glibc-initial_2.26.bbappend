python () {
    if d.getVar("TARGET_ARCH").find("riscv") >= 0:
        d.setVar("GLIBC_GIT_URI", "git://github.com/riscv/riscv-glibc")
        d.setVar("SRCBRANCH", "riscv-glibc-2.26")
        d.setVar("SRCREV", "e6a625aceade0948d1c1452006943a747e585bfc")
}

GLIBC_ADDONS := ""

#GLIBC_GIT_URI="git://github.com/riscv/riscv-glibc"
#SRCBRANCH="riscv-glibc-2.26"
#SRCREV="2f626de717a86be3a1fe39e779f0b179e13ccfbb"

EXTRA_OECONF_remove = "--enable-add-ons"
