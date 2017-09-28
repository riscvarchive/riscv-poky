SUMMARY = "A fragmentation avoiding general purpose malloc"
DESCRIPTION = "jemalloc is a general purpose malloc(3) implementation that emphasizes \
fragmentation avoidance and scalable concurrency support.  jemalloc first came \
into use as the FreeBSD libc allocator in 2005, and since then it has found its \
way into numerous applications that rely on its predictable behavior.  In 2010 \
jemalloc development efforts broadened to include developer support features \
such as heap profiling and extensive monitoring/tuning hooks.  Modern jemalloc \
releases continue to be integrated back into FreeBSD, and therefore versatility \
remains critical.  Ongoing development efforts trend toward making jemalloc \
among the best allocators for a broad range of demanding applications, and \
eliminating/mitigating weaknesses that have practical repercussions for real \
world applications."

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=6eb32e04d82c4a120f1682615e8ef214"

#SRCREV = "896ed3a8b3f41998d4fb4d625d30ac63ef2d51fb"
#SRC_URI = "\
#     git://github.com/jemalloc/jemalloc;branch=master;protocol=git \
#"

SRC_URI = "\
https://github.com/jemalloc/jemalloc/releases/download/5.0.1/jemalloc-5.0.1.tar.bz2 \
"
SRC_URI[md5sum] = "507f7b6b882d868730d644510491d18f"
SRC_URI[sha256sum] = "4814781d395b0ef093b21a08e8e6e0bd3dab8762f9935bbfb71679b0dea7c3e9"

#S = "${WORKDIR}/jemalloc-5.0.1"

EXTRA_OECONF += "--enable-autogen --disable-builddir"
EXTRA_OEMAKE_class-target = "LIBTOOLFLAGS='--tag=CC'"
inherit autotools-brokensep texinfo

do_configure() {
  cd ${S}
  ./autogen.sh ${CONFIGUREOPTS} ${EXTRA_OECONF}
}

SRC_URI_append += "file://jemalloc-riscv.patch"


