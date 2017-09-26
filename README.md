Yocto/OpenEmbedded RISC-V Port
==============================

`riscv-poky` is a full Linux Distribution for RISC-V, based on the Yocto Project and OpenEmbedded. It enables users to cross-compile a wide range of software for RISC-V and package them as file system images, or to be used with a package manager.

Maintainer  : Martin Maas (maas@eecs.berkeley.edu)   
Contributors : Sagar Karandikar   

Introduction
-----------

The Yocto Project is a Linux Distribution Generator. Instead of providing pre-compiled packages for a specific set of platforms, it is built around *recipes*, which are scripts that describe how to fetch, cross-compile and deploy particular packages on different platforms, and adapt them to different usage scenarios. Recipes also support *features* to describe variations between targets, such as different boards, processor extensions or available peripherals.

Yocto builds on the OpenEmbedded project, which is a collection of common recipes combined with a build tool (`bitbake`) that automatically tracks dependencies and parallelizes compilation tasks. A typical Yocto build will consist of:

1. Selecting a set of packages and features to build
2. Running bitbake, which will download the sources for all packages (including the cross-compile toolchain), build everything (while automatically managing the sysroots) and produce:

  * A Linux kernel image
  * A rootfs image (different file sytems are supported)
  * A set of packages for the target (RPM and DEB are both supported)
  * Any missing components for the host toolchain (including qemu if requested)
  * Any SDKs if requested (native, cross or canadian cross)

Yocto is an industrial-grade project, well-maintained and is an official part of the Linux Foundation. It is supported by numerous industrial partners, including AMD, Broadcom, Huawei, Intel, LG, Texas Instruments and others. Many companies use OpenEmbedded to generate the Linux distributions powering their embedded devices, including SDKs for development boards such as the [ZedBoard](https://github.com/Xilinx/meta-xilinx). Some additional features include:

1. Quality assurance throughout: The output of all compilation steps is checked for unexpected or missing files, all downloaded file need to have a matching checksum and LICENSE files are checked for compliance.
2. Quick feedback loop: When changing any input file, bitbake will rebuild only the parts of the distribution affected by it. It is also possible to use a graphical tool (Hob) to select packages to build and immediately launch the result in qemu for testing. Bitbake can even generate a build appliance that can be executed in a VM independent of the host system (which can be useful for continuous integration testing).
3. Robustness: Since Yocto builds the entire toolchain itself (including many of the host tools and libraries), it requires a small amount of dependencies on the host, which avoids many sources of incompatibilities.
4. Customizable: Yocto makes it very easy to add and customize features of a distribution. One mechanism it uses is the concept of a *layer*: Layers are directory trees storing recipes -- Yocto can overlay multiple layers, which makes it possible to selectively add features. For example, there can be a common ARM layer that contains everything required to run on ARM platforms, and different layers for different ARM development boards, each of which would include amendments and additions specific to the board.

The large number of available packages makes Yocto a very good starting point for porting a full Linux distribution: many packages will build with the basic Yocto distribution without requiring any modifications at all -- all the work to do with managing sysroots, dependencies, etc. is done by Yocto in a platform-independent way, so only packages that require real porting for RISC-V require changes to their recipes at all.

Getting Started
---------------

To get started, clone the `riscv/riscv-poky` repository (poky is the name of the distribution that Yocto generates):

```
git clone https://github.com/riscv/riscv-poky.git
cd riscv-poky
```

After cloning the repository, first initialize the OpenEmbedded environment:

```
source oe-init-build-env
```

This will automatically change into the build directory, which is where OpenEmbedded will do all of its work. This directory contains a number of subdirectories and files; these are the most important ones:

* `conf/local.conf`: This contains all information about the distribution to be built. This includes things such as target machines, which packages to build, whether to use a previously installed cross-compile toolchain or build a new one, and a whole lot more.
* `conf/bblayers.conf`: This lists the layers to use; for RISC-V, this should not be changed (since everything RISC-V-related is in the same layer at the moment).
* `tmp`: This is the main working directory which includes the unpacked sources for each package, build directories, sysroots and (eventually) the final images and packages.

To build a fully functional RISC-V image including tools such as Python, Perl and SSH, run the following command (note that this will automatically download and build the RISC-V toolchain, riscv-linux and riscv-qemu):

```
bitbake core-image-riscv
```

**Warning**: This will download a large amount of data over the network and use up a significant amount of space on disk.

The local.conf file is preconfigured to build for the `riscv64` machine by default. `core-image-minimal` is an image target, which contains a list of packages to build as well as instructions how to install them into an image.

Depending on the machine, the build process can take a very long time (remember that this downloads and builds dozens of packages, including riscv-tools, riscv-qemu and riscv-linux). Note that bitbake is very efficient at utilizing highly parallel machines since it can spawn off many jobs in parallel.

Once compilation has finished, you can run the resulting image from the build directory by calling:

```
<...>/build$ runspike riscv64
```

**Warning**: The current version of riscv-poky is incompatible with RISCVEMU, and support for this emulator has therefore been disabled (we hope to bring it back as soon as possible). Please use Spike for now. If you need a different emulator, please consider using the master branch (which is out of date and using privilieged spec 1.7) or helping us add Virtio support to riscv-qemu.

To run with QEMU, please use the command below:

```
<...>/build$ runqemu riscv64 nographic slirp
```

If you would prefer to run in RISCVEMU instead of Spike, you can call the following:

```
<...>/build$ runriscvemu riscv64
```

Maintaining the RISC-V Port
---------------------------

The RISC-V port already builds many relevant packages and tools (e.g., Python and GCC on the target), but many aspects will require straightening out over time. Please read this section if you would like to contribute to the RISC-V Yocto port. We would like to encourage you to submit pull requests for any packages that you port to RISC-V, so that we can grow riscv-poky into a complete Linux distribution.

The `poky-riscv` repository is a fork of the master branch from Yocto's main repository, `poky.git`. This is a development branch, and we are trying to track changes closely.  Currently, changes to poky.git fall into two categories:

* The `meta-riscv` layer contains recipes for RISC-V-specific packages that often override the ones that come with Yocto. The reason for this is to work around features not yet supported in riscv-tools (e.g. kernel command line options in qemu) or that the source has to be cloned from riscv repositories rather than downloaded from GNU mirrors. As a result, many recipes in this directory are not as clean as they could be and the goal is eventually to merge RISC-V-specific changes back into the mainline Yocto recipes (the way that other architectures such as ARM are supported) rather than needing a separate layer for RISC-V.
* Changes to the remainder of the Yocto reporisoty are changes in recipes and scripts required to support RISC-V as a new ISA. Any change outside the `meta-riscv` directory should be considered something that we may eventually upstream to the Yocto Project.

A good policy would be to work completely in the meta-riscv layer and move recipes outside the meta-riscv layer as RISC-V changes are upstreamed into other projects (such as the Linux kernel or GCC).

Details and HOWTOs
------------------

* **GCC Versions**: By default, riscv-poky uses the `riscv-gnu-toolchain` based on GCC 4.9.2. If for any reason you would like to use the old `riscv-tools` toolchain, you can look at the uncommented lines towards the end of `meta-riscv/conf/distro/poky-riscv-tiny.conf`. Note that the old toolchain is not supported at this point and may cause compilation problems.

* **Distributions**: In addition to the default distrbiution (poky-riscv with core-image-riscv), it is also possible to build a minimal distribution using BusyBox, without SysV boot, package management and most applications. To do this, set the distribution to `poky-riscv-tiny` in local.conf (`DISTRO ?= "poky-riscv-tiny"`), and build `core-image-minimal`.

* **Adding packages**: To add a package to the generated image, edit `meta-riscv/recipes-core/images/core-image-riscv.bb` and add the desired package to the `IMAGE_INSTALL` list.

* **Building an SDK**: A useful feature of riscv-poky is the ability to build an SDK, which is a package containing the cross-compile toolchain and all of its dependencies, which can be installed on any x86-64 Linux machine. Use `bitbake meta-toolchain` to build an SDK.

### TODO List/Known Problems

Here are some immediate problems that are currently work in progress:

* There is currently no networking support in either QEMU or Spike. It should be relatively simple to restore this by adding a VirtIO network device to the QEMU setup. If you make this change, please consider submitting a pull request.
* There are currently some warnings about sysconf being skipped -- this is expected.
* There is currently no support for `/dev/misc/rtc`, and the resulting error message is expected.
* Occassionally, riscv-linux will crash with `main-loop: WARNING: I/O thread spun for 1000 iterations`

Further Reading
---------------

* About the Yocto Project: https://www.yoctoproject.org
* Quick Start Guide: http://www.yoctoproject.org/docs/current/yocto-project-qs/yocto-project-qs.html
* Reference Manual: http://www.yoctoproject.org/docs/current/ref-manual/ref-manual.html
* BitBake Manual (especially useful when building recipes): http://www.yoctoproject.org/docs/1.6/bitbake-user-manual/bitbake-user-manual.html
