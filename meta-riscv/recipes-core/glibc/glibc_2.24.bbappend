GLIBC_GIT_URI="git://github.com/riscv/riscv-glibc"
SRCBRANCH="multilib"
SRCREV="682f132334cd73e94423965325a441b9cb2da93b"

EXTRA_OECONF_remove = "--enable-add-ons"

SELECTED_OPTIMIZATION_append = "-Wno-error"
