# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)
#
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
HOMEPAGE = "https://github.com/lxc/lxc/"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "git://github.com/lxc/lxc.git;protocol=https;branch=stable-2.0;tag=lxc-2.0.3"
SRCREV = "lxc-2.0.3"
#SRC_URI = "https://linuxcontainers.org/downloads/lxc/lxc-2.0.3.tar.gz"
#SRC_URI[md5sum] = "4a5654005924ec6f52eb9719520547d4"
#SRC_URI[sha256sum] = "82df40a0cdd44639ee677d560be95348de48afa93a10a20e959dadb4431ee8d6"

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

