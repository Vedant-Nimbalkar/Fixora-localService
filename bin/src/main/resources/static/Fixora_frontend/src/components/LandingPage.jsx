// src/components/LandingPage.js
import React, { useState, useEffect } from 'react';
import { Wrench, Menu, X, Star, ArrowLeft } from 'lucide-react';

const LandingPage = ({ navigate }) => {
  const [isScrolled, setIsScrolled] = useState(false);
  const [mobileMenuOpen, setMobileMenuOpen] = useState(false);

  useEffect(() => {
    const handleScroll = () => setIsScrolled(window.scrollY > 20);
    window.addEventListener('scroll', handleScroll);
    return () => window.removeEventListener('scroll', handleScroll);
  }, []);

  return (
    <div className="min-h-screen bg-[#F9FAFB] text-[#111827] font-sans selection:bg-blue-100 selection:text-blue-900 scroll-smooth">

      {/* Navbar */}
      <nav className={`fixed w-full z-50 transition-all duration-300 ${isScrolled ? 'bg-white/90 backdrop-blur-md shadow-sm py-3' : 'bg-transparent py-5'}`}>
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex justify-between items-center">
            {/* Logo */}
            <div className="flex items-center gap-2 cursor-pointer" onClick={() => navigate('/')}>
              <div className="w-8 h-8 rounded-lg bg-gradient-to-br from-blue-600 to-violet-600 flex items-center justify-center shadow-md">
                <Wrench className="w-5 h-5 text-white" />
              </div>
              <span className="text-2xl font-bold tracking-tight bg-clip-text text-transparent bg-gradient-to-r from-blue-600 to-violet-600">
                Fixora
              </span>
            </div>

            {/* Desktop Menu */}
            <div className="hidden md:flex items-center space-x-8">
              {['Home', 'Services', 'How it Works', 'Features'].map((item) => (
                <a key={item} href={`#${item.toLowerCase().replace(/\s+/g, '-')}`} className="text-sm font-medium text-gray-600 hover:text-blue-600 transition-colors">
                  {item}
                </a>
              ))}
            </div>

            {/* Desktop CTA */}
            <div className="hidden md:flex">
              <a href="#portals" className="px-5 py-2.5 text-sm font-medium text-white bg-blue-600 rounded-lg hover:bg-blue-700 hover:scale-105 hover:shadow-lg hover:shadow-blue-600/20 transition-all duration-300 cursor-pointer">
                Login / Sign Up
              </a>
            </div>

            {/* Mobile Menu Button */}
            <div className="md:hidden flex items-center">
              <button onClick={() => setMobileMenuOpen(!mobileMenuOpen)} className="text-gray-600 hover:text-gray-900 focus:outline-none" aria-label="Toggle menu">
                {mobileMenuOpen ? <X className="w-6 h-6" /> : <Menu className="w-6 h-6" />}
              </button>
            </div>
          </div>
        </div>

        {/* Mobile Menu Panel */}
        {mobileMenuOpen && (
          <div className="md:hidden absolute top-full left-0 w-full bg-white shadow-xl border-t border-gray-100 py-4 px-4 flex flex-col gap-4">
            {['Home', 'Services', 'How it Works', 'Features'].map((item) => (
              <a key={item} href={`#${item.toLowerCase().replace(/\s+/g, '-')}`} className="text-base font-medium text-gray-700 p-2 rounded-md hover:bg-gray-50" onClick={() => setMobileMenuOpen(false)}>
                {item}
              </a>
            ))}
            <a href="#portals" onClick={() => setMobileMenuOpen(false)} className="w-full mt-2 px-5 py-3 text-sm font-medium text-white bg-blue-600 rounded-lg shadow-md text-center">
              Login / Sign Up
            </a>
          </div>
        )}
      </nav>

      {/* Hero Section */}
      <section id="home" className="relative pt-32 pb-20 lg:pt-48 lg:pb-32 overflow-hidden">
        <div className="absolute top-0 left-1/2 -translate-x-1/2 w-[1000px] h-[500px] bg-gradient-to-br from-blue-400/20 to-violet-400/20 blur-3xl rounded-full -z-10 opacity-50 pointer-events-none"></div>
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex flex-col lg:flex-row items-center gap-12 lg:gap-8">
            <div className="w-full lg:w-1/2 text-center lg:text-left">
              <div className="inline-flex items-center gap-2 px-3 py-1 rounded-full bg-blue-50 border border-blue-100 text-blue-600 text-sm font-medium mb-6">
                <span className="flex h-2 w-2 rounded-full bg-blue-600"></span> Smarter Local Services
              </div>
              <h1 className="text-4xl md:text-5xl lg:text-6xl font-bold leading-tight mb-6 tracking-tight text-gray-900">
                Find <span className="text-transparent bg-clip-text bg-gradient-to-r from-blue-600 to-emerald-500">Trusted Local</span><br className="hidden md:block"/> Services Instantly
              </h1>
              <p className="text-lg md:text-xl text-gray-600 mb-8 max-w-2xl mx-auto lg:mx-0">
                Connect with highly-rated plumbers, electricians, cleaners, and technicians in your area. Book in seconds, track in real-time.
              </p>
              <div className="flex flex-col sm:flex-row items-center justify-center lg:justify-start gap-4">
                <button onClick={() => navigate('/services')} className="w-full sm:w-auto px-8 py-4 text-base font-semibold text-white bg-blue-600 rounded-lg shadow-lg shadow-blue-600/30 hover:bg-blue-700 hover:-translate-y-1 transition-all duration-300">
                  Book a Service
                </button>
                <button onClick={() => navigate('/partner/register')} className="w-full sm:w-auto px-8 py-4 text-base font-semibold text-gray-700 bg-white border border-gray-200 rounded-lg shadow-sm hover:border-gray-300 hover:bg-gray-50 hover:-translate-y-1 transition-all duration-300">
                  Become a Provider
                </button>
              </div>
              <div className="mt-10 flex items-center justify-center lg:justify-start gap-4 text-sm text-gray-500">
                <div className="flex -space-x-2">
                  {[1, 2, 3, 4].map((i) => (
                    <div key={i} className={`w-8 h-8 rounded-full border-2 border-white flex items-center justify-center text-xs text-white font-bold
                      ${i === 1 ? 'bg-blue-500' : i === 2 ? 'bg-emerald-500' : i === 3 ? 'bg-violet-500' : 'bg-orange-400'}`}>
                      {String.fromCharCode(64 + i)}
                    </div>
                  ))}
                </div>
                <div>
                  <div className="flex items-center text-amber-400">
                    <Star className="w-4 h-4 fill-current" /><Star className="w-4 h-4 fill-current" /><Star className="w-4 h-4 fill-current" /><Star className="w-4 h-4 fill-current" /><Star className="w-4 h-4 fill-current" />
                  </div>
                  <p className="mt-0.5"><strong className="text-gray-900 font-semibold">4.8/5</strong> from 10k+ users</p>
                </div>
              </div>
            </div>

            {/* Hero Visual Mockup */}
            <div className="w-full lg:w-1/2 relative flex justify-center lg:justify-end perspective-1000">
              <div className="relative w-full max-w-md h-[500px]">
                <div className="absolute inset-0 bg-gradient-to-tr from-emerald-400/20 via-blue-500/20 to-violet-500/20 blur-2xl rounded-full"></div>
                <div className="absolute inset-x-4 top-10 bottom-0 bg-white rounded-3xl shadow-2xl border border-gray-100 overflow-hidden transform transition-transform hover:scale-[1.02] duration-500 flex flex-col">
                  <div className="absolute inset-0 bg-[url('data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSI0MCIgaGVpZ2h0PSI0MCIgdmlld0JveD0iMCAwIDQwIDQwIj4gPHBhdGggZD0iTTEyIDEwYzAgMCAyIDAgMiAybDEgMiAyIDEgMSAyIDEgMiAtMiA0eiIgZmlsbD0ibm9uZSIgc3Ryb2tlPSIjMDAwMDAwIiBzdHJva2UtbGluZWNhcD0ibm9uZSIgZmlsbC1vcGFjaXR5PSIuM1Y5XzIiLz48L3N2Zz4=')] bg-no-repeat bg-center bg-cover rounded-xl z-20"></div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </div>
  );
}

export default LandingPage;