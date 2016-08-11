# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)
#
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

HOMEPAGE = "https://github.com/lxc/lxc/"

# No information for SRC_URI yet (only an external source tree was specified)
SRCREV ?= "${AUTOREV}"
SRC_URI = "git://github.com/lxc/lxc.git;protocol=https;branch=master"

S = "${WORKDIR}/git"
# NOTE: the following library dependencies are unknown, ignoring: gnutls apparmor selinux seccomp
#       (this is based on recipes that have previously been built and packaged)
RDEPENDS_${PN} += "glibc-xilinx libcap bash"
DEPENDS_${PN} += "glibc-xilinx libcap"

FILES_${PN}-dbg += "{prefix}/src/"
FILES_${PN}-dbg += "{prefix}/src/*"
FILES_${PN}-dbg += "{sbindir}/.debug/"
FILES_${PN}-dbg += "{sbindir}/.debug/*"
FILES_${PN}-dbg += "{bindir}/.debug/"
FILES_${PN}-dbg += "{bindir}/.debug/*"
FILES_${PN}-dbg += "{libdir}/.debug/"
FILES_${PN}-dbg += "{libdir}/.debug/*"
FILES_${PN}-dbg += "${libdir}/lxc/hooks/.debug/unmount-namespace"
FILES_${PN}-dbg += "${libdir}/lxc/.debug/"
FILES_${PN}-dbg += "${libdir}/lxc/.debug/*"

# NOTE: if this software is not capable of being built in a separate build directory
# from the source, you should replace autotools with autotools-brokensep in the
# inherit line
#inherit pkgconfig autotools-brokensep
inherit pkgconfig autotools

# Specify any options you want to pass to the configure script using EXTRA_OECONF:
EXTRA_OECONF = "--with-init-script=sysvinit \
                --enable-capabilities \
                --prefix=/usr \
                --sysconfdir=/etc \
                --localstatedir=/var \
                --libexecdir=/usr/lib \
                "

do_configure() {
#    build_p=$(pwd)
#    cd ${S} ; ./autogen.sh; cd $build_p
    cd ${S} ; ./autogen.sh; cd ${B}
    oe_runconf
}

