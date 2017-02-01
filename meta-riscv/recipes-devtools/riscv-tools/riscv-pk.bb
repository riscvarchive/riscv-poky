SUMMARY = "RISC-V Proxy Kernel"
DESCRIPTION = "RISC-V Proxy Kernel"
LICENSE = "GPLv2+"

LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.GPLv2;md5=751419260aa954499f7abaabaa882bbe"

SRCREV = "f6b2274af4a91763ecdb94600d7d54d5f7f262b5"
SRC_URI = "git://github.com/riscv/riscv-pk.git \
           file://riscvemu-pk.patch"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

LDFLAGS_append = " -Wl,--build-id=none -Wl,--strip-debug"

EXTRA_OECONF += "--with-payload=${TMPDIR}/deploy/images/qemuriscv64/vmlinux"

inherit autotools

DEPENDS = "riscv-fesvr-native riscv-spike-native riscv-linux riscvemu-native"

INHIBIT_PACKAGE_STRIP = "1"

INSANE_SKIP_${PN} += "installed-vs-shipped"

TARGET_CFLAGS = ""

S = "${WORKDIR}/git"

do_configure_prepend () {
        if [ ! -e ${S}/acinclude.m4 ]; then
                cp ${S}/aclocal.m4 ${S}/acinclude.m4
        fi
}

do_install_prepend () {
        install -d ${STAGING_DIR_NATIVE}/${datadir}/riscv-pk
        install -m 755 ${WORKDIR}/build/bbl ${STAGING_DIR_NATIVE}${datadir}/riscv-pk
        ${OBJCOPY} -O binary ${WORKDIR}/build/bbl ${WORKDIR}/build/bbl_linux.bin
}

PROVIDES_${PN}_class += "${PN}-bbl"
PACKAGES_class += " ${PN}-bbl"
