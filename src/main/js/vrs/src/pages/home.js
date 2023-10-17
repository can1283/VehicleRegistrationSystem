import React from "react";
import Background from "../assets/wave-haikei.svg"
import {Link} from "react-router-dom";
import CarImage from '../assets/car.png'
import Logo from "../assets/NCarsWhite.svg";

const Home = () => {
    return (
        <div className="w-full h-screen flex flex-col relative overflow-hidden">
            <img src={Logo} alt={"logo"} className={"w-56 absolute p-6"}/>
            <h1 className={"absolute text-cyan-50 text-8xl mt-48 ml-4 user-select-none"}>Vehicle <br/> Registration <br/> System
            </h1>
            <p className={"absolute mt-[480px] p-4 w-[820px] text-lg text-blue-200"}>Welcome to our Vehicle Registration System!
                Simplify vehicle management with us. Add, remove, register, or search for vehicles effortlessly. Your
                one-stop solution for all things related to your vehicles. Start now!</p>
            <div className="absolute top-4 right-4 space-x-4 z-10">
                <Link to="/login">
                    <button
                        className="bg-blue-400 text-white px-4 py-2 rounded-lg shadow-2xl user-select-none">Log In
                    </button>
                </Link>
                <Link to="/register">
                    <button
                        className="bg-green-500 text-white px-4 py-2 rounded-lg shadow-2xl user-select-none">Sign Up
                    </button>
                </Link>
            </div>

            <img className="object-cover w-full h-full" src={Background} alt="background"
                 style={{pointerEvents: "none", userSelect: "none"}}/>

            <img id={"car"} src={CarImage} alt="background"
                 style={{pointerEvents: "none", userSelect: "none"}}/>

            <div className="absolute text-center p-4 bottom-0">
                <a
                    href="https://github.com/can1283"
                    target="_blank"
                    rel="noopener noreferrer"
                    className="transition font-monospace text-cyan-50 hover:underline hover:text-cyan-50"
                >
                    My GitHub Account
                </a>
            </div>
        </div>
    )
}

export default Home;