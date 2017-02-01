SRCREV = "2e8a797d9eaef6ad5bd072899b25a1e6e6efce21"
SRC_URI = "\
     git://github.com/riscv/riscv-binutils-gdb;branch=__archive__;protocol=git \
     file://0002-configure-widen-the-regexp-for-SH-architectures.patch \
     file://0003-Point-scripts-location-to-libdir.patch \
     file://0004-Only-generate-an-RPATH-entry-if-LD_RUN_PATH-is-not-e.patch \
     file://0005-Explicitly-link-with-libm-on-uclibc.patch \
     file://0006-Use-libtool-2.4.patch \
     file://0007-Add-the-armv5e-architecture-to-binutils.patch \
     file://0008-don-t-let-the-distro-compiler-point-to-the-wrong-ins.patch \
     file://0009-warn-for-uses-of-system-directories-when-cross-linki.patch \
     file://0010-Fix-rpath-in-libtool-when-sysroot-is-enabled.patch \
     file://0011-Change-default-emulation-for-mips64-linux.patch \
     file://0012-Add-support-for-Netlogic-XLP.patch \
     file://0013-fix-the-incorrect-assembling-for-ppc-wait-mnemonic.patch \
"
