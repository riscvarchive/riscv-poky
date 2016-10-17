require recipes-devtools/qemu/qemu.inc

SRC_URI = "gitsm://github.com/riscv/riscv-qemu.git;destsuffix=${S}"
SRCREV_pn-qemu-native = "9bfcd4717b3010eb7efc50057232e92ecb741cac"
SRCREV_pn-nativesdk-qemu = "9bfcd4717b3010eb7efc50057232e92ecb741cac"

SRC_URI_remove_class-native = "\
    file://fix-libcap-header-issue-on-some-distro.patch \
    file://cpus.c-qemu_cpu_kick_thread_debugging.patch \
    "

LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=441c28d2cf86e15a37fa47e15a72fbac"

QEMU_TARGETS = "riscv64"

EXTRA_OECONF_remove = "--disable-numa --disable-lzo --disable-opengl --disable-gnutls"

COMPATIBLE_HOST_class-target_mips64 = "null"

do_install() {
    export STRIP="true"
    autotools_do_install
    install -d ${D}${datadir}/qemu

    # Prevent QA warnings about installed ${localstatedir}/run
    if [ -d ${D}${localstatedir}/run ]; then rmdir ${D}${localstatedir}/run; fi
}
