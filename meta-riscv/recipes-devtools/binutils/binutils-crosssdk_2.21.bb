require binutils-cross-riscv_${PV}.bb

inherit crosssdk

PN = "binutils-crosssdk-${TARGET_ARCH}"

PROVIDES = "virtual/${TARGET_PREFIX}binutils-crosssdk"

#NOTE: Not tested yet, probably doesn't work for riscv yet.
#SRC_URI += "file://relocatable_sdk.patch"

do_configure_prepend () {
	sed -i 's#/usr/local/lib /lib /usr/lib#${SDKPATHNATIVE}/lib ${SDKPATHNATIVE}/usr/lib /usr/local/lib /lib /usr/lib#' ${S}/ld/configure.tgt
}
