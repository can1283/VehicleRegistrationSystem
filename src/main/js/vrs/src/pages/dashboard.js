import React, {useEffect, useState} from "react";
import axios from "axios";
import {Link, useNavigate} from "react-router-dom";
import Logo from '../assets/NCarsBlack.svg'
import '../App.css'

const Dashboard = () => {

    const [userName, setUserName] = useState(' ');
    const [firstName, setFirstName] = useState(' ');
    const [lastName, setLastName] = useState(' ');
    const [mail, setMail] = useState('');
    const [city, setCity] = useState('');
    const [vehicles, setVehicles] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');
    const [isProfilePopoverOpen, setIsProfileePopoverOpen] = useState(false);
    const [isSortedPopoverOpen, setIsSortedPopoverOpen] = useState(false);
    const [sortingCriteria, setSortingCriteria] = useState("brand");
    const [selectedSortingLabel, setSelectedSortingLabel] = useState("Brand");
    const [isAlertVisible, setIsAlertVisible] = useState(false);
    const navigation = useNavigate();

    const fetchSortedVehicles = (sortBy) => {
        const userId = localStorage.getItem('id');

        if (userId) {
            axios.get(`http://localhost:8080/api/vehicles/user/${userId}/vehicles/sorted?sortDirection=ASC&sortBy=${sortBy}`)
                .then((response) => {
                    setVehicles(response.data);
                    setSelectedSortingLabel(sortBy === "model" ? "Model" : sortBy === "modelYear" ? "Model Year" : sortBy === "name" ? "Name" : "Brand");
                })
                .catch((error) => {
                    console.error("Error fetching sorted vehicles:", error);
                });
        }
    };


    useEffect(() => {
        fetchSortedVehicles(sortingCriteria);
    }, [sortingCriteria]);

    const deleteVehicle = (vehicleId) => {
        axios.delete(`http://localhost:8080/api/vehicles/${vehicleId}`)
            .then(() => {
                setVehicles((prevVehicles) => prevVehicles.filter((vehicle) => vehicle.id !== vehicleId));
                setIsAlertVisible(true);
                setTimeout(() => {
                    setIsAlertVisible(false);
                }, 3000);
            })
            .catch((error) => {
                console.error(`Error deleting vehicle with ID ${vehicleId}:`, error);
            });
    };

    const userId = localStorage.getItem('id');

    const deleteUser = (userId) => {
        if (userId) {
            axios.delete(`http://localhost:8080/api/users/${userId}`)
                .then(() => {
                    handleLogOut();
                    navigation('/');
                })
                .catch((error) => {
                    console.error(`Error deleting user with ID ${userId}:`, error);
                });
        }
    };


    useEffect(() => {
        fetchSortedVehicles(sortingCriteria);
    }, [sortingCriteria]);


    useEffect(() => {
        const userId = localStorage.getItem('id');

        if (userId) {
            axios.get(`http://localhost:8080/api/users/${userId}`)
                .then(response => {
                    setUserName(response?.data?.userName);
                    setFirstName(response?.data?.firstName);
                    setLastName(response?.data?.lastName);
                    setMail(response?.data?.mail);
                    setCity(response?.data?.city);
                    setVehicles(response?.data?.vehicles);
                })
                .catch(error => {
                    console.error("Error retrieving username:", error);
                });
        }
    }, []);

    const handleLogOut = () => {
        localStorage.removeItem("id")
    }

    const toggleProfilePopover = () => {
        setIsProfileePopoverOpen(!isProfilePopoverOpen);
    };

    const toggleSortedPopover = () => {
        setIsSortedPopoverOpen(!isSortedPopoverOpen);
    };

    const closeProfilePopover = () => {
        setIsProfileePopoverOpen(false);
    };

    const closeSortedPopover = () => {
        setIsSortedPopoverOpen(false);
    };

    function showAlert(message) {
        const alertDiv = document.getElementById("alert");
        alertDiv.innerHTML = `<div class="bg-red-500 text-white px-4 py-2 rounded-md shadow-md">${message}</div>`;
        alertDiv.style.display = "block";

        setTimeout(() => {
            alertDiv.style.display = "none";
        }, 3000);
    }

    return (
        <div className="wrapper flex flex-col select-none">
            <div className="header p-4 flex justify-between items-center bg-gradient-to-r from-indigo-50 via-indigo-100 to-indigo-200">
                <div className="left">
                    <img src={Logo} alt="logo" className="w-40" />
                </div>
                <div className="right flex gap-2 p-2 items-center">
                    <Link to="/add">
                        <button className="bg-indigo-600 hover:bg-indigo-700 shadow text-white px-4 py-2 rounded-lg">
                            Add Vehicles
                        </button>
                    </Link>
                    <div className="cursor-pointer shadow bg-white text-dark w-52 h-10 border-2 border-gray-300 rounded-lg flex justify-center items-center p-2" onClick={toggleSortedPopover}>
                        Sorted By {selectedSortingLabel} ↓
                        <div className="relative">
                            {isSortedPopoverOpen && (
                                <div className="absolute cursor-default z-10 inline-block w-64 text-sm text-gray-500 transition-opacity duration-300 bg-white border border-gray-200 rounded-lg shadow-2xl opacity-100" style={{ transform: "translateX(-76%)", marginTop: "27px" }}>
                                    <div className="px-3 py-2">
                                        <ul className="ml-2">
                                            <li className="cursor-pointer text-lg text-gray-900" onClick={() => setSortingCriteria("model")}>Model</li>
                                            <li className="cursor-pointer text-lg text-gray-900" onClick={() => setSortingCriteria("modelYear")}>Model Year</li>
                                            <li className="cursor-pointer text-lg text-gray-900" onClick={() => setSortingCriteria("name")}>Name</li>
                                            <li className="cursor-pointer text-lg text-gray-900" onClick={() => setSortingCriteria("brand")}>Brand</li>
                                        </ul>
                                    </div>
                                    <div data-popper-arrow></div>
                                </div>
                            )}
                        </div>
                    </div>
                    <input
                        type="text"
                        placeholder="Search vehicle..."
                        className="p-2 border-2 border-gray-300 rounded-lg outline-none shadow"
                        value={searchTerm}
                        onChange={(e) => setSearchTerm(e.target.value)}
                    />
                    <div className="cursor-pointer bg-white w-10 h-10 rounded-full border-2 border-gray-300 shadow flex justify-center p-1">
                        <div className="relative">
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="black" className="w-6 h-6 cursor-pointer text-dark flex" title="Open Popover" onClick={toggleProfilePopover}>
                                <path strokeLinecap="round" strokeLinejoin="round" d="M15.75 6a3.75 3.75 0 11-7.5 0 3.75 3.75 0 017.5 0zM4.501 20.118a7.5 7.5 0 0114.998 0A17.933 17.933 0 0112 21.75c-2.676 0-5.216-.584-7.499-1.632z" />
                            </svg>
                            {isProfilePopoverOpen && (
                                <div className="absolute z-10 inline-block w-64 text-sm text-gray-500 transition-opacity duration-300 bg-white border border-gray-200 rounded-lg shadow-2xl opacity-100" style={{ top: "160%", left: "30%", transform: "translateX(-90%)" }}>
                                    <div className="px-3 py-2 bg-gray-100 border-b border-gray-200 rounded-t-lg">
                                        <h3 className="font-semibold cursor-default text-gray-900">{userName} - {firstName} {lastName}</h3>
                                        <h3 className="font-semibold cursor-default text-gray-900 mt-2">{mail}</h3>
                                        <h3 className="font-semibold cursor-default text-gray-900 mt-2">{city}</h3>
                                    </div>
                                    <div className="px-3 py-2">
                                        <Link to="/password">
                                            <button className="mt-2 border rounded p-1">Change password</button>
                                        </Link>
                                        <br />
                                        <Link to="/">
                                            <button onClick={handleLogOut} className="mt-2 border rounded p-1">Log out</button>
                                            <br />
                                        </Link>
                                        <Link to="/">
                                            <button onClick={() => deleteUser(userId)} className="mt-2 border rounded p-1 hover:text-indigo-600">Delete Account</button>
                                        </Link>
                                    </div>
                                    <div data-popper-arrow></div>
                                </div>
                            )}
                        </div>
                    </div>
                </div>
            </div>
            <div className="p-3 mb-16">
                <table className="w-full">
                    <thead className="">
                    <tr className="bg-gray-50 border">
                        <th className="border font-medium text-center p-2 text-xl">Id</th>
                        <th className="border font-medium text-center p-2 text-xl">Name</th>
                        <th className="border font-medium text-center p-2 text-xl">Brand</th>
                        <th className="border font-medium text-center p-2 text-xl">Model</th>
                        <th className="border font-medium text-center p-2 text-xl">Model Year</th>
                        <th className="border font-medium text-center p-2 text-xl">Plate Code</th>
                        <th className="border font-medium text-center p-2 text-xl">Active</th>
                        <th className="border font-medium text-center p-2 text-xl">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    {vehicles.map((vehicle, index) => {
                        const shouldDisplay = Object.values(vehicle).some((value) =>
                            value && value.toString().toLowerCase().includes(searchTerm.toLowerCase())
                        );

                        if (!shouldDisplay) {
                            return null;
                        }

                        return (
                            <tr key={index} className="bg-white border">
                                <td className="bg-white border px-2 text-xl text-gray-600">{vehicle.id}</td>
                                <td className="bg-white border px-2 text-xl text-gray-600">{vehicle.name}</td>
                                <td className="bg-white border px-2 text-xl text-gray-600">{vehicle.brand}</td>
                                <td className="bg-white border px-2 text-xl text-gray-600">{vehicle.model}</td>
                                <td className="bg-white border px-2 text-xl text-gray-600">{vehicle.modelYear}</td>
                                <td className="bg-white border px-2 text-xl text-gray-600">{vehicle.plateCode}</td>
                                <td className="bg-white border px-2 text-xl text-gray-600 text-center">
                                    {vehicle.active ? (
                                        <div className="flex items-center justify-center gap-3">
                                            <div className="w-3 h-3 rounded-full bg-green-400"></div>
                                            <p>Yes</p>
                                        </div>
                                    ) : (
                                        <div className="flex items-center justify-center gap-3">
                                            <div className="w-3 h-3 rounded-full bg-red-500"></div>
                                            <p className="ml-1.5">No</p>
                                        </div>
                                    )}
                                </td>
                                <td className="flex justify-evenly p-1.5">
                                    <Link to={`/edit/${vehicle.id}`}>
                                        <button className="bg-indigo-600 hover:bg-indigo-700 text-white px-8 py-2 rounded-lg">Edit</button>
                                    </Link>
                                    <button onClick={() => deleteVehicle(vehicle.id)} className="bg-red-600 hover:bg-red-700 text-white px-8 py-2 rounded-lg">Delete</button>
                                </td>
                            </tr>
                        );
                    })}
                    </tbody>
                </table>
            </div>
            {isAlertVisible && (
                <div className="p-2 flex items-center justify-content-center text-lg text-red-100 rounded-lg bg-red-600 w-96 bottom-4 right-0 mr-4 fixed z-30">
                    <span className="font-medium">Vehicle Deleted!</span>
                </div>
            )}
            <div className="w-full h-20 bottom-0 fixed" style={{ background: "linear-gradient(0deg, rgba(255,255,255,0.8841036414565826) 0%, rgba(255,255,255,0) 100%)" }}></div>
        </div>
    )
}

export default Dashboard;
