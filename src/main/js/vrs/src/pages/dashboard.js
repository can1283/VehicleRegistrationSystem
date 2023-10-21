import React, {useEffect, useState} from "react";
import axios from "axios";
import {Link, useNavigate} from "react-router-dom";
import '../App.css'
import {AiOutlineDelete, AiOutlineDown} from "react-icons/ai";
import {FiEdit3, FiLogOut} from "react-icons/fi";
import {LiaExchangeAltSolid} from "react-icons/lia";
import {FaCircleNodes} from "react-icons/fa6";
import {LuMail} from "react-icons/lu";
import {GoLocation} from "react-icons/go";
import {IoMdRemove} from "react-icons/io";

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

    const [currentPage, setCurrentPage] = useState(1);
    const [itemsPerPage, setItemsPerPage] = useState(12);
    const [totalItems, setTotalItems] = useState(0);
    const indexOfLastItem = currentPage * itemsPerPage;
    const indexOfFirstItem = indexOfLastItem - itemsPerPage;
    const currentItems = vehicles.slice(indexOfFirstItem, indexOfLastItem);

    useEffect(() => {
        const newTotalItems = vehicles.length;
        setTotalItems(newTotalItems);
    }, [vehicles, currentPage]);

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


    return (
        <div className="wrapper flex flex-col select-none">
            <div className="header p-4 flex justify-between items-center bg-indigo-50">
                <div className="left">
                    <FaCircleNodes className={"text-[45px]"}/>
                </div>
                <div className="right flex gap-2 p-2 items-center ">
                    <Link to="/add">
                        <button className="bg-blue-500 transition hover:bg-indigo-700 shadow text-white px-4 py-2 rounded-lg">
                            Add Vehicles
                        </button>
                    </Link>
                    <div
                        className="cursor-pointer shadow bg-white text-dark  h-10 border-2 border-indigo-300 rounded-lg flex justify-center items-center p-2 w-auto"
                        onClick={toggleSortedPopover}>
                        Sorted By {selectedSortingLabel} <AiOutlineDown className={"ml-1.5"}/>
                        <div className="relative">
                            {isSortedPopoverOpen && (
                                <div
                                    className="absolute cursor-default z-10 inline-block w-64 text-sm text-gray-500 transition-opacity duration-300 bg-white border border-gray-200 rounded-lg shadow-2xl opacity-100"
                                    style={{transform: "translateX(-76%)", marginTop: "27px"}}>
                                    <div className="px-3 py-2">
                                        <ul className="ml-2">
                                            <li className="cursor-pointer text-lg text-gray-900"
                                                onClick={() => setSortingCriteria("model")}>Model
                                            </li>
                                            <li className="cursor-pointer text-lg text-gray-900"
                                                onClick={() => setSortingCriteria("modelYear")}>Model Year
                                            </li>
                                            <li className="cursor-pointer text-lg text-gray-900"
                                                onClick={() => setSortingCriteria("name")}>Name
                                            </li>
                                            <li className="cursor-pointer text-lg text-gray-900"
                                                onClick={() => setSortingCriteria("brand")}>Brand
                                            </li>
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
                        className="p-1.5 border-2 border-indigo-300 rounded-lg outline-none shadow mr-4"
                        value={searchTerm}
                        onChange={(e) => setSearchTerm(e.target.value)}
                    />
                    <div
                        className="cursor-pointer bg-white w-[55px] h-[55px] rounded-full border-2 border-indigo-600 outline outline-indigo-300 flex justify-center items-center"
                        onClick={toggleProfilePopover}>
                        <div className="relative">
                            <div
                                className={"font-medium text-2xl text-indigo-800"}>{userName.slice(0, 1).toUpperCase()}</div>
                            {isProfilePopoverOpen && (
                                <div
                                    className="absolute z-10 inline-block w-80 text-sm text-gray-500 transition-opacity duration-300 bg-white rounded-lg shadow-2xl opacity-100"
                                    style={{top: "160%", left: "30%", transform: "translateX(-90%)"}}>
                                    <div className="px-3 py-2 bg-indigo-900 text-white border-b border-gray-200 rounded-t-lg">
                                        <h3 className="font-medium text-xl cursor-default">{firstName} {lastName}</h3>
                                        <h3 className="font-medium cursor-default mt-2"><LuMail className={"inline-block text-white text-xl"}/> {mail}</h3>
                                        <h3 className="font-medium cursor-default mt-4"><GoLocation className={"inline-block text-white text-xl"}/> {city}</h3>
                                    </div>
                                    <div className="px-3 py-2">
                                        <Link to="/password">
                                            <button className="mt-4 text-[17px] p-1 hover:text-indigo-600">
                                                <LiaExchangeAltSolid
                                                    className={"inline-block text-gray-900 text-xl"}/> Change password
                                            </button>
                                        </Link>
                                        <br/>
                                        <Link to="/">
                                            <button onClick={handleLogOut}
                                                    className="mt-4 text-[17px] p-1 hover:text-indigo-600"><FiLogOut
                                                className={"inline-block text-gray-900 text-xl"}/> Log out
                                            </button>
                                            <br/>
                                        </Link>
                                        <Link to="/">
                                            <button onClick={() => deleteUser(userId)}
                                                    className="mt-4 text-[17px]  p-1 hover:text-indigo-600">
                                                <AiOutlineDelete
                                                    className={"inline-block text-gray-900 text-xl"}/> Delete Account
                                            </button>
                                        </Link>
                                    </div>
                                    <div data-popper-arrow></div>
                                </div>
                            )}
                        </div>
                    </div>
                </div>
            </div>
            <div className="wrapper">
                <table className="w-full">
                    <thead className="">
                    <tr className="bg-gray-50 border">
                        <th className="border font-medium text-center p-2 text-xl">#</th>
                        <th className="border font-medium text-left p-2 text-xl">Name</th>
                        <th className="border font-medium text-left p-2 text-xl">Brand</th>
                        <th className="border font-medium text-left p-2 text-xl">Model</th>
                        <th className="border font-medium text-center p-2 text-xl">Model Year</th>
                        <th className="border font-medium text-center p-2 text-xl">Plate Code</th>
                        <th className="border font-medium text-center p-2 text-xl">Active</th>
                        <th className="border font-medium text-center p-2 text-xl">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    {currentItems.map((vehicle, index) => {
                        const shouldDisplay = Object.values(vehicle).some((value) =>
                            value && value.toString().toLowerCase().includes(searchTerm.toLowerCase())
                        );

                        if (!shouldDisplay) {
                            return null;
                        }

                        return (
                            <tr key={index} className="bg-white border">
                                <td className="bg-white border px-2 text-xl text-gray-600 text-center">{vehicle.id}</td>
                                <td className="bg-white border px-2 text-xl text-gray-600">{vehicle.name}</td>
                                <td className="bg-white border px-2 text-xl text-gray-600">{vehicle.brand}</td>
                                <td className="bg-white border px-2 text-xl text-gray-600">{vehicle.model}</td>
                                <td className="bg-white border px-2 text-xl text-gray-600 text-center">{vehicle.modelYear}</td>
                                <td className="bg-white border px-2 text-xl text-gray-600 text-center">{vehicle.plateCode}</td>
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
                                        <button
                                            className="bg-white border border-indigo-300 transition hover:bg-indigo-300 px-4 py-2 rounded-lg"> <FiEdit3 className={"inline-block text-blue-950 text-xl"}/>
                                        </button>
                                    </Link>
                                    <button onClick={() => deleteVehicle(vehicle.id)}
                                            className="bg-white border border-red-400 hover:bg-red-400 text-white px-4 py-2 rounded-lg"> <IoMdRemove className={"inline-block text-red-950 text-xl"}/>
                                    </button>
                                </td>
                            </tr>
                        );
                    })}
                    </tbody>
                </table>
            </div>
            <div className="pagination flex fixed bottom-8 left-1/2 transform -translate-x-1/2 shadow-2xl rounded-lg">
                <button
                    onClick={() => setCurrentPage(currentPage - 1)}
                    disabled={currentPage === 1}
                    className="bg-blue-500 text-white py-2 px-4 rounded-l-lg"
                >
                    Previous
                </button>
                <span className="bg-blue-100 text-blue-500 py-2 px-4">
                    Page {currentPage} / {Math.ceil(totalItems / itemsPerPage)}
                </span>
                <button
                    onClick={() => setCurrentPage(currentPage + 1)}
                    disabled={currentPage === Math.ceil(totalItems / itemsPerPage)}
                    className="bg-blue-500 text-white py-2 px-4 rounded-r-lg"
                >
                    Next
                </button>
            </div>
            {isAlertVisible && (
                <div
                    className="transition-all p-2 flex items-center justify-center text-lg rounded-lg bg-red-100 text-red-500 w-fit border border-red-500 bottom-8 right-0 mr-8 fixed z-30">
                    <span className="font-medium">Vehicle Deleted!</span>
                </div>
            )}
        </div>
    )
}

export default Dashboard;
