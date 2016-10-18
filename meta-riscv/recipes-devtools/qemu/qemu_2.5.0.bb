require recipes-devtools/qemu/qemu.inc

SRC_URI = "gitsm://github.com/martinmaas/riscv-qemu.git;destsuffix=${S};branch=legacy-poky"
SRCREV_pn-qemu-native = "b0cf38d08a4779ec12d7189878b2e57d2b56ec6b"
SRCREV_pn-nativesdk-qemu = "b0cf38d08a4779ec12d7189878b2e57d2b56ec6b"

SRC_URI_remove_class-native = "\
    file://fix-libcap-header-issue-on-some-distro.patch \
    file://cpus.c-qemu_cpu_kick_thread_debugging.patch \
    "

LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=441c28d2cf86e15a37fa47e15a72fbac"

QEMU_TARGETS = "riscv"

EXTRA_OECONF_remove = "--disable-numa --disable-lzo --disable-opengl --disable-gnutls"

COMPATIBLE_HOST_class-target_mips64 = "null"

do_install() {
    export STRIP="true"
    autotools_do_install
    install -d ${D}${datadir}/qemu

    # Prevent QA warnings about installed ${localstatedir}/run
    if [ -d ${D}${localstatedir}/run ]; then rmdir ${D}${localstatedir}/run; fi
}
