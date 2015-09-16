RDEPENDS_${PN} := "\
    binutils-cross-canadian-${@' binutils-cross-canadian-'.join(all_multilib_tune_values(d,'TRANSLATED_TARGET_ARCH').split())} \
    gcc-cross-canadian-${@' gcc-cross-canadian-'.join(all_multilib_tune_values(d, 'TRANSLATED_TARGET_ARCH').split())} \
    meta-environment-${MACHINE} \
    nativesdk-riscv-fesvr \
    nativesdk-riscv-spike \
    "
