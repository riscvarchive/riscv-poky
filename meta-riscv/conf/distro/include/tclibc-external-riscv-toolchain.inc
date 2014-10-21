#
# External RISC-V glibc specific configuration
#
# Based on git://riscv-project.org/git/meta-riscv.git
# meta-riscv/meta-riscv-extras/conf/distro/include/tclibc-external-riscv.inc
#

LIBCEXTENSION = "${@['', '-gnu'][(d.getVar('ABIEXTENSION', True) or '') != '']}"

# Add glibc overrides to the overrides for eglibc.
LIBCOVERRIDE = ":libc-glibc"
OVERRIDES .= "${LIBCOVERRIDE}"

PREFERRED_PROVIDER_virtual/libiconv ?= "external-riscv-toolchain"
PREFERRED_PROVIDER_virtual/libintl ?= "external-riscv-toolchain"
PREFERRED_PROVIDER_virtual/libc ?= "external-riscv-toolchain"
PREFERRED_PROVIDER_virtual/libc-locale ?= "external-riscv-toolchain"

PREFERRED_PROVIDER_virtual/nativesdk-libiconv ?= "nativesdk-eglibc"
PREFERRED_PROVIDER_virtual/nativesdk-libintl ?= "nativesdk-eglibc"
PREFERRED_PROVIDER_virtual/nativesdk-libc ?= "nativesdk-eglibc"

LIBC_DEPENDENCIES = "libsegfault \
		     glibc \
		     glibc-dbg \
		     glibc-dev \
		     glibc-utils \
		     glibc-thread-db \
		     ${@get_libc_locales_dependencies(d)}"

LIBC_LOCALE_DEPENDENCIES = "\
	glibc-localedata-i18n \
	glibc-gconv-ibm850 \
	glibc-gconv-cp1252 \
	glibc-gconv-iso8859-1 \
	glibc-gconv-iso8859-15 \
	locale-base-en-us \
	locale-base-en-gb"

def get_libc_locales_dependencies(d):
    if 'libc-locales' in (d.getVar('DISTRO_FEATURES', True) or '').split() :
        return d.getVar('LIBC_LOCALE_DEPENDENCIES', True) or ''
    else:
        return ''
