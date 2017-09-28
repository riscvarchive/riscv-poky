SUMMARY = "Hiredis is a minimalistic C client library for the Redis database."

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=d84d659a35c666d23233e54503aaea51"

DEPENDS += "redis"

SRC_URI = "\
https://github.com/redis/hiredis/archive/v0.13.3.tar.gz \
"
SRC_URI[md5sum] = "43dca1445ec6d3b702821dba36000279"
SRC_URI[sha256sum] = "717e6fc8dc2819bef522deaca516de9e51b9dfa68fe393b7db5c3b6079196f78"

do_configure_prepend() {
 # cd ${S}
}

do_compile_prepend() {
  cd ${S}
}


do_install_prepend() {
  cd ${S}
}

S = "${WORKDIR}/hiredis-0.13.3/"


EXTRA_OEMAKE_class-target += "LIBTOOLFLAGS='--tag=CC' CC='"${WORKDIR}/recipe-sysroot-native/usr/bin/riscv64-poky-linux/riscv64-poky-linux-gcc"' STLIB_CMD='"${WORKDIR}/recipe-sysroot-native/usr/bin/riscv64-poky-linux/riscv64-poky-linux-gcc-ar -rs"'"

inherit autotools


