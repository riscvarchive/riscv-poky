#
# Based on git://arago-project.org/git/meta-arago.git
# meta-arago/meta-arago-extras/recipes-core/meta/external-riscv-toolchain.inc
#

def arg_get_main_version(d):
	import os,bb
	if os.path.exists(d.getVar('TOOLCHAIN_PATH', d, 1)+'/version'):
		f = open(d.getVar('TOOLCHAIN_PATH', d, 1)+'/version', 'r')
		l = f.readlines();
		f.close();
		for s in l:
			if s.find('Version') > 0:
				ver = s.split()[2]
				return ver
		return None

def arg_get_gcc_version(d):
	import subprocess,os,bb
	if os.path.exists(d.getVar('TOOLCHAIN_PATH', d, 1)+'/bin/'+d.getVar('TARGET_PREFIX', d, 1)+'gcc'):
		return subprocess.Popen([d.getVar('TOOLCHAIN_PATH', d, 1)+'/bin/'+d.getVar('TARGET_PREFIX', d, 1)+'gcc', '-v'], stderr=subprocess.PIPE).communicate()[1].splitlines()[-1].split()[2]

def arg_get_bfd_version(d):
	import subprocess,os,bb
	if os.path.exists(d.getVar('TOOLCHAIN_PATH', d, 1)+'/bin/'+d.getVar('TARGET_PREFIX', d, 1)+'as'):
		return subprocess.Popen([d.getVar('TOOLCHAIN_PATH', d, 1)+'/bin/'+d.getVar('TARGET_PREFIX', d, 1)+'as', '-v', '--version'], stderr=subprocess.STDOUT,stdout=subprocess.PIPE).communicate()[0].splitlines()[0].split()[3]

def arg_get_gdb_version(d):
	import subprocess,os,bb
	if os.path.exists(d.getVar('TOOLCHAIN_PATH', d, 1)+'/bin/'+d.getVar('TARGET_PREFIX', d, 1)+'gdb'):
		return subprocess.Popen([d.getVar('TOOLCHAIN_PATH', d, 1)+'/bin/'+d.getVar('TARGET_PREFIX', d, 1)+'gdb', '-v'],stdout=subprocess.PIPE).communicate()[0].splitlines()[0].split()[-1]

def arg_get_libc_version(d):
	import os,bb
	if os.path.exists(d.getVar('TOOLCHAIN_SYSPATH', d, 1)+'/lib/'):
		for file in os.listdir(d.getVar('TOOLCHAIN_SYSPATH', d, 1)+'/lib/'):
			if file.find('libc-') == 0:
				return file[5:-3]
		return None

def arg_get_kernel_version(d):
	import os,bb
	if os.path.exists(d.getVar('TOOLCHAIN_SYSPATH', d, 1)+'/usr/include/linux/'):
		f = open(d.getVar('TOOLCHAIN_SYSPATH', d, 1)+'/usr/include/linux/version.h', 'r')
		l = f.readlines();
		f.close();
		for s in l:
			if s.find('LINUX_VERSION_CODE') > 0:
				ver = int(s.split()[2])
				maj = ver / 65536
				ver = ver % 65536
				min = ver / 256
				ver = ver % 256
				return str(maj)+'.'+str(min)+'.'+str(ver)
		return None

ARG_VER_MAIN := "${@arg_get_main_version(d)}"
ARG_VER_GCC := "${@arg_get_gcc_version(d)}"
ARG_VER_BFD := "${@arg_get_bfd_version(d)}"
ARG_VER_GDB := "${@arg_get_gdb_version(d)}"
ARG_VER_LIBC := "${@arg_get_libc_version(d)}"
ARG_VER_KERNEL := "${@arg_get_kernel_version(d)}"

# Licenses set for main components of the toolchain:
# (g)libc is always LGPL version 2 (or later)
# gcc has switched from GPL version 2 (or later) to version 3 (or later) after 4.2.1,
#    see this announcement - http://gcc.gnu.org/ml/gcc-announce/2007/msg00003.html
# libgcc and libstdc++ always had exceptions to GPL called Runtime Library Exception, but
#    it was based on GPL version 2 (or later), until new GPL version 3 (or later) exception
#    was introduced on 27 Jan 2009 - http://gcc.gnu.org/ml/gcc-announce/2009/msg00000.html
#    and http://www.gnu.org/licenses/gcc-exception.html, which was several days after
#    gcc 4.3.3 was released - http://gcc.gnu.org/releases.html
# gdb/gdbserver version 6.6 was the last one under GPL version 2 (or later), according
#    to the release schedule - http://www.gnu.org/software/gdb/schedule/
# binutils version 2.17 was the last one under GPL version 2 (or later), according
#    to the published releases - http://ftp.gnu.org/gnu/binutils/
ARG_LIC_LIBC := "LGPLv2.1+"
ARG_LIC_GCC := "${@["GPLv3+", "GPLv2+"][arg_get_gcc_version(d) <= "4.2.1"]}"
ARG_LIC_RLE := "${@["GPLv3+ with GCC RLE", "GPLv2+ with GCC RLE"][arg_get_gcc_version(d) <= "4.3.3"]}"
ARG_LIC_GDB := "${@["GPLv3+", "GPLv2+"][arg_get_gdb_version(d) <= "6.6"]}"
ARG_LIC_BFD := "${@["GPLv3+", "GPLv2+"][arg_get_bfd_version(d) <= "2.17"]}"
