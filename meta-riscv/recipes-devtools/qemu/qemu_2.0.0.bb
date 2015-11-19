require recipes-devtools/qemu/qemu.inc

SRC_URI = "gitsm://github.com/ucb-bar/riscv-qemu.git;destsuffix=${S}"
SRCREV_pn-qemu-native = "74bcf6c908938d78e1be5c0fc0638f8f89e678b8"
SRCREV_pn-nativesdk-qemu = "74bcf6c908938d78e1be5c0fc0638f8f89e678b8"

LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=441c28d2cf86e15a37fa47e15a72fbac"

QEMU_TARGETS = "riscv"

EXTRA_OECONF_remove = "--disable-numa --disable-lzo --disable-opengl --disable-gnutls"

EXTRA_OECONF += "--disable-riscv-htif"

SRC_URI_remove_class-native = "\
    file://fix-libcap-header-issue-on-some-distro.patch \
    file://cpus.c-qemu_cpu_kick_thread_debugging.patch \
    "

do_sanitize_sources() {
    # These .git files point to a nonexistent path "../.git/modules" and will confuse git
    # if it tries to recurse into those directories.
    rm -f ${S}/dtc/.git ${S}/pixman/.git
}

addtask sanitize_sources after do_unpack before do_patch

do_install () {
	export STRIP="true"
	autotools_do_install
	install -d ${D}${datadir}/qemu
}


do_install_append() {
    # Prevent QA warnings about installed ${localstatedir}/run
    if [ -d ${D}${localstatedir}/run ]; then rmdir ${D}${localstatedir}/run; fi
}
